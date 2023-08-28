package org.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mysql.ConncetDB;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.EnsUtils;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;

public class INFURAWSS_YunGouExchange extends WebSocketClient {
    static Dotenv dotenv = Dotenv.load();
    static String INFURA_WSS_MAIN = dotenv.get("INFURA_WSS_MAIN");
    static Connection connection = ConncetDB.getConnect();
    static final int RECONNECT_INTERVAL = 1000; // 初始重连间隔
    static String YunGouExchangeAddress = "0x0000006c517ed32ff128b33f137bb4ac31b0c6dd";
    static String OrderFulfilled_Topic = "0xb4061f266d4bf413befd152ec5e3eb87822f75e00136129e3abe8c406bd8c64b";
    static String subscribeRequest = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"eth_subscribe\",\"params\":[\"logs\", {\"address\": \"%s\",\"topics\":[\"%s\"]}]}";
    static String subscribeRequest_ = String.format(subscribeRequest, YunGouExchangeAddress, OrderFulfilled_Topic);
    private static WebSocketClient client;

    public INFURAWSS_YunGouExchange(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public INFURAWSS_YunGouExchange(URI serverURI) {
        super(serverURI);
    }

    public INFURAWSS_YunGouExchange(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        System.out.println("opened connection");
        send(subscribeRequest_);

    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        // handleMessage(message);

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

    public static void main(String[] args) throws URISyntaxException {

        try {
            client = new INFURAWSS_YunGouExchange(new URI(INFURA_WSS_MAIN));
            client.connect();
            Thread.sleep(RECONNECT_INTERVAL);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        while (true) {
            if (client.isClosed()) {
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

    private static void reconnectToINFURA() throws URISyntaxException {
        System.out.println("isClosed: " + client.isClosed());
        client.close();
        client = new INFURAWSS_YunGouExchange(new URI(INFURA_WSS_MAIN));
        client.connect();
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
            String data = result.getString("data");
            JSONArray topics = result.getJSONArray("topics");
            // ERC20: topics.length() == 3
            // ERC721: topics.length() == 4

            if (topics.length() == 4) {
                String offerer = topics.getString(1);
                String offerToken = topics.getString(2);
                String buyer = topics.getString(3);
                String offererAddress = FunctionReturnDecoder.decodeAddress(offerer);
                String offerTokenAddress = FunctionReturnDecoder.decodeAddress(offerToken);
                String buyerAddress = FunctionReturnDecoder.decodeAddress(buyer);

                List<TypeReference<Type>> outputParameters = new ArrayList<>(6);

                outputParameters.add((TypeReference) new TypeReference<Bytes32>() {
                });
                outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                });
                outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                });
                outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                });
                outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                });
                outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                });

                List<Type> decodedString = FunctionReturnDecoder.decode(
                        data, outputParameters);
                byte[] orderHash_ = (byte[]) decodedString.get(0).getValue();
                // byte[] to hexstring
                String orderHash = Numeric.toHexString(orderHash_);
                BigInteger offerTokenId = (BigInteger) decodedString.get(1).getValue();
                BigInteger buyAmount = (BigInteger) decodedString.get(2).getValue();
                BigInteger totalPayment = (BigInteger) decodedString.get(3).getValue();
                BigInteger totalRoyaltyFee = (BigInteger) decodedString.get(4).getValue();
                BigInteger totalPlatformFee = (BigInteger) decodedString.get(5).getValue();

                // if (!fromAddress.equals(EnsUtils.EMPTY_ADDRESS)
                // && !toAddress.equals(EnsUtils.EMPTY_ADDRESS)) {

                // String tokenId = topics.getString(3);

                // BigInteger blockNumberBig = Numeric.toBigInt(blockNumber);
                // BigInteger tokenIdBig = Numeric.toBigInt(tokenId);

                // try {

                // String insertQuery = "INSERT IGNORE INTO
                // aggregator_ethan.event_transfer_erc721 "
                // + "(token, tokenId, fromAddress, toAddress, blockNumber, transactionHash) "
                // + "VALUES(?,?,?,?,?,?)";
                // PreparedStatement preparedStatement =
                // connection.prepareStatement(insertQuery,
                // PreparedStatement.RETURN_GENERATED_KEYS);

                // // Set values for parameters
                // preparedStatement.setString(1, token);
                // preparedStatement.setString(2, tokenIdBig.toString());
                // preparedStatement.setString(3, fromAddress);
                // preparedStatement.setString(4, toAddress);
                // preparedStatement.setInt(5, blockNumberBig.intValue());
                // preparedStatement.setString(6, transactionHash);

                // int rowsAffected = preparedStatement.executeUpdate();
                // if (rowsAffected > 0) {
                // // 获取生成的主键值
                // ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                // if (generatedKeys.next()) {
                // long generatedId = generatedKeys.getLong(1);
                // System.out.println("event_transfer_erc721 insert Id: " + generatedId);
                // }

                // generatedKeys.close();
                // preparedStatement.close();
                // }

                // } catch (SQLException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }

                // }

            }

        }

    }

}