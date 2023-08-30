package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mysql.ConncetDB;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.EnsUtils;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostTransferEventsETH {
    static Dotenv dotenv = Dotenv.load();
    // INFURA_HTTP_MAIN QUIKNODE_HTTP_BSC
    static String INFURA_HTTP_MAIN = dotenv.get("INFURA_HTTP_MAIN");
    static Connection connection = ConncetDB.getConnect();
    static String lastBlockNumberHex = null;
    static final String DEAD_ADDRESS = "0x000000000000000000000000000000000000dead";
    static final String EMPTY_ADDRESS = "0x0000000000000000000000000000000000000000";
    static final long INTERVAL_BLOCK = 12;

    public static void main(String[] args) throws IOException, InterruptedException {
        // postTransferEventsInRange("18024106", "18017461");

        executeTransferEventERC721();
    }

    public static void executeTransferEventERC721() throws IOException, InterruptedException {
        // 什么时候开始呢？当前时间戳 - 最新的区块时间戳
        long interval = getSystemTimestamp() - getLatestBlockTimestamp();
        System.out.println(interval);
        if (interval > 0) {
            Thread.sleep((INTERVAL_BLOCK - interval + 1) * 1000);
        }
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable printTask = () -> {
            if (lastBlockNumberHex != null) {
                System.out.println("lastBlockNumber:" + Numeric.toBigInt(lastBlockNumberHex));
            }

            try {
                postTransferEvents(lastBlockNumberHex);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        };
        // 初始延迟0秒，然后每隔12秒执行一次任务
        executorService.scheduleAtFixedRate(printTask, 0, INTERVAL_BLOCK, TimeUnit.SECONDS);
    }

    public static void postTransferEventsInRange(String fromBlock, String toBlock) {
        OkHttpClient client = new OkHttpClient();
        String fromBlockHex = Numeric.toHexStringWithPrefix(new BigInteger(fromBlock));
        String toBlockHex = Numeric.toHexStringWithPrefix(new BigInteger(toBlock));
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody_ = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"fromBlock\":\"%s\",\"toBlock\":\"%s\",\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}],\"id\":1}";
        String requestBody = String.format(requestBody_, fromBlockHex, toBlockHex);
        Request request = new Request.Builder()
                .url(INFURA_HTTP_MAIN)
                .post(RequestBody.create(mediaType, requestBody))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                JSONObject json = new JSONObject(responseBody);

                JSONArray results = json.getJSONArray("result");
                // System.out.println(results.toString());
                if (results.length() > 0) {
                    // handle Response Result
                    handleResponseResult(results);
                }
            } else {
                System.out.println("POST request failed with response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void postTransferEvents(String fromBlockHex) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody_ = null;
        String requestBody = null;
        if (fromBlockHex == null) {

            requestBody = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}],\"id\":1}";

        } else {
            requestBody_ = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"fromBlock\":\"%s\",\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}],\"id\":1}";
            // fromBlockHex + 1
            fromBlockHex = Numeric.toHexStringWithPrefix(Numeric.toBigInt(fromBlockHex).add(BigInteger.ONE));
            System.out.println("Current Block:" + Numeric.toBigInt(fromBlockHex));
            requestBody = String.format(requestBody_, fromBlockHex);
        }

        Request request = new Request.Builder()
                .url(INFURA_HTTP_MAIN)
                .post(RequestBody.create(mediaType, requestBody))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                JSONObject json = new JSONObject(responseBody);

                JSONArray results = json.getJSONArray("result");
                // System.out.println(results.toString());
                if (results.length() > 0) {
                    // handle Response Result
                    handleResponseResult(results);
                }
            } else {
                System.out.println("POST request failed with response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleResponseResult(JSONArray results) throws IOException {

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String transactionHash = result.getString("transactionHash");
            String blockNumber = result.getString("blockNumber");
            String token = result.getString("address");
            JSONArray topics = result.getJSONArray("topics");
            lastBlockNumberHex = blockNumber;
            if (topics.length() == 4) {
                String from = topics.getString(1);
                String to = topics.getString(2);
                String fromAddress = FunctionReturnDecoder.decodeAddress(from);
                String toAddress = FunctionReturnDecoder.decodeAddress(to);

                if (!fromAddress.equals(EMPTY_ADDRESS)
                        && !toAddress.equals(EMPTY_ADDRESS)
                        && !toAddress.equals(DEAD_ADDRESS)) {

                    String tokenId = topics.getString(3);

                    BigInteger blockNumberBig = Numeric.toBigInt(blockNumber);

                    BigInteger tokenIdBig = Numeric.toBigInt(tokenId);

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

        // System.out.println("Inset Data Time:" + getSystemTimestamp());
    }

    private static long getLatestBlockTimestamp() throws IOException {
        Web3j web3j = Web3j.build(new HttpService(INFURA_HTTP_MAIN));

        EthBlock.Block latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
                .send().getBlock();

        BigInteger timestamp = latestBlock.getTimestamp();
        // System.out.println("Timestamp of the Latest Block: " + timestamp);
        // long currentSeconds = Instant.now().getEpochSecond();
        // System.out.println(currentSeconds);
        // System.out.println(System.currentTimeMillis() / 1000);
        return timestamp.longValue();
    }

    private static long getBlockTimestamp(BigInteger blockNumber) throws IOException {
        Web3j web3j = Web3j.build(new HttpService(INFURA_HTTP_MAIN));

        EthBlock.Block latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false)
                .send().getBlock();

        BigInteger timestamp = latestBlock.getTimestamp();
        return timestamp.longValue();
    }

    private static long getSystemTimestamp() throws IOException {

        long currentSeconds = Instant.now().getEpochSecond();

        return currentSeconds;
    }
}
