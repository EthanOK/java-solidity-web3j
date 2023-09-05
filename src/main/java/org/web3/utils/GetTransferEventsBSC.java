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
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetTransferEventsBSC {
    static Dotenv dotenv = Dotenv.load();
    // BSC_API_KEY
    static String ANKR_HTTP_BSC = dotenv.get("ANKR_HTTP_BSC");
    static String BSC_API_KEY = dotenv.get("BSC_API_KEY");
    static Connection connection = ConncetDB.getConnect();
    static int lastBlockNumber;
    static int startBlockNumber;
    static final String DEAD_ADDRESS = "0x000000000000000000000000000000000000dead";
    static final String EMPTY_ADDRESS = "0x0000000000000000000000000000000000000000";
    static final long INTERVAL_BLOCK = 6 * 1000;

    public static void main(String[] args) throws IOException, InterruptedException {
        // postTransferEventsInRange("18024106", "18017461");

        startBlockNumber = getLastBlockNumber().intValue();
        executeTransferEventERC721();

    }

    public static void executeTransferEventERC721() throws IOException, InterruptedException {

        while (true) {
            try {
                System.out.println("startBlockNumber:" + startBlockNumber);
                try {
                    getTransferEvents(startBlockNumber);

                    System.out.println("lastBlockNumber:" + lastBlockNumber);
                    System.out.println("````````````````````````");
                    // lastBlockNumber + 1
                    startBlockNumber = lastBlockNumber + 1;

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("has error");
                    startBlockNumber = lastBlockNumber;
                }

                // 等待 INTERVAL_BLOCK
                Thread.sleep(INTERVAL_BLOCK);
            } catch (InterruptedException e) {
                e.printStackTrace();
                startBlockNumber = lastBlockNumber;
            }
        }

    }

    public static void getTransferEvents(int fromBlock) throws IOException {
        String getURL = "https://api.bscscan.com/api?module=logs&action=getLogs&fromBlock=" + fromBlock
                + "&toBlock=latest&topic0=0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef&"
                + "apikey=" + BSC_API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(getURL).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                JSONObject json = new JSONObject(responseBody);

                JSONArray results = json.getJSONArray("result");
                // System.out.println(results.toString());
                if (results.length() > 0) {
                    // handle Response Result
                    handleResponseResult(results);
                } else {
                    lastBlockNumber = fromBlock;
                }
            } else {
                System.out.println("POST request failed with response code: " + response.code());
                // 如果访问失败 本区块访问失败
                lastBlockNumber = fromBlock - 1;

            }
        } catch (IOException e) {
            e.printStackTrace();
            // 如果访问失败
            lastBlockNumber = fromBlock - 1;
        }

    }

    private static void handleResponseResult(JSONArray results) throws IOException {

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String transactionHash = result.getString("transactionHash");
            String blockNumber = result.getString("blockNumber");
            String token = result.getString("address");
            JSONArray topics = result.getJSONArray("topics");
            lastBlockNumber = Numeric.toBigInt(blockNumber).intValue();
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

                        String insertQuery = "INSERT IGNORE INTO aggregator_ethan.event_transfer_erc721_bsc "
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
                                System.out.println(
                                        "event_transfer_erc721 insert Id: " + generatedId + " in " + blockNumberBig
                                                + " " + transactionHash);

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
        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC));

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
        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC));

        EthBlock.Block latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false)
                .send().getBlock();

        BigInteger timestamp = latestBlock.getTimestamp();
        return timestamp.longValue();
    }

    private static BigInteger getLastBlockNumber() throws IOException {
        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC));

        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();

        BigInteger number = blockNumber.getBlockNumber();

        return number;
    }

    private static long getSystemTimestamp() throws IOException {

        long currentSeconds = Instant.now().getEpochSecond();

        return currentSeconds;
    }
}
