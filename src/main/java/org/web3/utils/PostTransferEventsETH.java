package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static void main(String[] args) {
        // postTransferEventsInRange("18017459", "18017461");

        // TODO: 每12秒请求一次，获得最新区块数据
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable printTask = () -> {
            postTransferEvents();
        };
        // 初始延迟0秒，然后每隔10秒执行一次任务
        executorService.scheduleAtFixedRate(printTask, 0, 12, TimeUnit.SECONDS);
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

    public static void postTransferEvents() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}],\"id\":1}";
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

    private static void handleResponseResult(JSONArray results) {
        /*
         * {
         * "address": "0xf6afc05fccea5a53f22a3e39ffee861e016bd9a0",
         * "blockHash":
         * "0xcb2a112831cc2a7ab0d6e93b0dda0aba046de1391c294da381f8e34e049198fc",
         * "blockNumber": "0x11297f1",
         * "data": "0x00000000000000000000000000000000000000000003ae67cbce6ebf24144000",
         * "logIndex": "0x2",
         * "removed": false,
         * "topics": [
         * "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef",
         * "0x000000000000000000000000120051a72966950b8ce12eb5496b5d1eeec1541b",
         * "0x000000000000000000000000e977791b2cd891e5dcd54d3a530ae413e8d2f242"
         * ],
         * "transactionHash":
         * "0x24106b7d2950fa908d441381ab10fe8848305e81b901ac2e40f83771e1f64a62",
         * "transactionIndex": "0x2"
         * }
         */
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String transactionHash = result.getString("transactionHash");
            String blockNumber = result.getString("blockNumber");
            String token = result.getString("address");
            JSONArray topics = result.getJSONArray("topics");

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
