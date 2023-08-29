package org.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.math.BigInteger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mysql.ConncetDB;
import org.web3.utils.PostTransferEventsInRange;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.utils.EnsUtils;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;

public class INFURAWebSocketClient extends WebSocketClient {
    static Dotenv dotenv = Dotenv.load();
    static String INFURA_WSS_MAIN = dotenv.get("INFURA_WSS_MAIN");
    static Connection connection = ConncetDB.getConnect();
    static final int RECONNECT_INTERVAL = 1000; // 初始重连间隔
    static String subscribeRequest = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"eth_subscribe\",\"params\":[\"logs\", {\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}]}";
    private static WebSocketClient client;
    static BigInteger lastBlockNumber;

    public INFURAWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public INFURAWebSocketClient(URI serverURI) {
        super(serverURI);
    }

    public INFURAWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        System.out.println("opened connection");
        send(subscribeRequest);

    }

    @Override
    public void onMessage(String message) {
        // handleMessage
        handleMessage(message);

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);

    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        try {
            client = new INFURAWebSocketClient(new URI(INFURA_WSS_MAIN));
            client.connect();
            Thread.sleep(RECONNECT_INTERVAL);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        while (true) {
            if (client.isClosed()) {
                // 如果已经关闭，推送记录的最后一次 blockNumber 进队列
                // push lastBlockNumber
                // System.out.println(lastBlockNumber);
                // 处理队列 （lastBlockNumber, lastBlockNumber + 1）
                System.out.println("Reconnecting to INFURA...");
                reconnectToINFURA();
            }
            try {
                Thread.sleep(RECONNECT_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void reconnectToINFURA() throws URISyntaxException, InterruptedException {
        // System.out.println("isClosed: " + client.isClosed());
        client.close();
        client = new INFURAWebSocketClient(new URI(INFURA_WSS_MAIN));
        client.connect();
        Thread.sleep(6000);
        // 处理中断的区块数据
        PostTransferEventsInRange.postTransferEventsInRange(lastBlockNumber.toString(),
                lastBlockNumber.add(new BigInteger("2")).toString());

    }

    private void handleMessage(String message) {
        // String 转 Json
        JSONObject json = new JSONObject(message);

        if (json.has("method")) {
            JSONObject params = json.getJSONObject("params");
            JSONObject result = params.getJSONObject("result");
            String transactionHash = result.getString("transactionHash");
            String blockNumber = result.getString("blockNumber");
            String token = result.getString("address");

            JSONArray topics = result.getJSONArray("topics");
            // ERC20: topics.length() == 3
            // ERC721: topics.length() == 4

            if (topics.length() == 4) {
                String from = topics.getString(1);
                String to = topics.getString(2);
                String fromAddress = FunctionReturnDecoder.decodeAddress(from);
                String toAddress = FunctionReturnDecoder.decodeAddress(to);
                if (!fromAddress.equals(EnsUtils.EMPTY_ADDRESS)
                        && !toAddress.equals(EnsUtils.EMPTY_ADDRESS)) {

                    String tokenId = topics.getString(3);

                    BigInteger blockNumberBig = Numeric.toBigInt(blockNumber);

                    BigInteger tokenIdBig = Numeric.toBigInt(tokenId);

                    lastBlockNumber = blockNumberBig;

                    // TODO: check data of on sale
                    try {

                        String encodeData = FunctionEncoder
                                .encodeConstructor(Arrays.<Type>asList(new Address(token), new Address(fromAddress),
                                        new Address(toAddress),
                                        new Uint256(tokenIdBig),
                                        new Uint256(blockNumberBig)));
                        String encodeDataHash = Hash.sha3("0x" + encodeData);

                        String insertQuery = "INSERT IGNORE INTO aggregator_ethan.event_transfer_erc721 "
                                + "(token, tokenId, fromAddress, toAddress, blockNumber, transactionHash, encodeDataHash) "
                                + "VALUES(?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                                PreparedStatement.RETURN_GENERATED_KEYS);

                        // Set values for parameters
                        preparedStatement.setString(1, token);
                        preparedStatement.setString(2, tokenIdBig.toString());
                        preparedStatement.setString(3, fromAddress);
                        preparedStatement.setString(4, toAddress);
                        preparedStatement.setInt(5, blockNumberBig.intValue());
                        preparedStatement.setString(6, transactionHash);
                        preparedStatement.setString(7, encodeDataHash);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            // 获取生成的主键值
                            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                long generatedId = generatedKeys.getLong(1);
                                System.out.println("event_transfer_erc721 insert Id: " + generatedId);
                            }

                            generatedKeys.close();
                            preparedStatement.close();
                        }

                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }

        }

    }

}