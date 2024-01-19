package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;

public class GetYGIOConvertEvent {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");
    static String ConvertTopic = "0xf223d34fa7bf5b0d07f699392b827f6573db287faff53a0ff1d6baff29796029";

    // 获取指定区块号的 区块哈希
    static BigInteger startBlockNumber = new BigInteger("10392353");

    public static void main(String[] args) throws IOException {
        Web3j web3j = Web3j
                .build(new HttpService(RPC));

        // 死循环 6s执行一次
        while (true) {
            try {

                // TODO:1 从数据库读取待查寻区块号
                BigInteger startBlockNumber_ = startBlockNumber;

                BigInteger nextBlockNumber = GetYGIOConvertEventByBlockNumber(web3j, startBlockNumber_.toString());

                // TODO:2 将待查寻区块号写入数据库
                startBlockNumber = nextBlockNumber;

                Thread.sleep(6000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static BigInteger GetYGIOConvertEventByBlockNumber(Web3j web3j, String blockNumber) throws IOException {
        BigInteger blockNumber_ = new BigInteger(blockNumber);
        // 1:ethGetBlockByNumber
        EthBlock.Block latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber_), false)
                .send().getBlock();
        if (latestBlock == null) {
            System.out.println("BlockNumber: " + blockNumber_.toString() + " 区块还没产生");
            return blockNumber_;
        }
        System.out.println("BlockNumber: " + latestBlock.getNumber());

        System.out.println("BlockHash: " + latestBlock.getHash());
        String blockHash = latestBlock.getHash();

        EthFilter filter = new EthFilter(blockHash)
                .addOptionalTopics(ConvertTopic);

        // Only get data no listen
        // 2:ethGetLogs
        EthLog ethLog = web3j.ethGetLogs(filter).send();
        List<LogResult> logs = ethLog.getLogs();
        if (logs.size() > 0) {
            handleLogsResult(logs);
        }

        return blockNumber_.add(BigInteger.ONE);

    }

    private static void handleLogsResult(List<LogResult> logs) throws IOException {
        for (LogResult log : logs) {
            JSONObject result = new JSONObject(log.get());
            String transactionHash = result.getString("transactionHash");
            BigInteger blockNumber = result.getBigInteger("blockNumber");
            String tokenAddress = result.getString("address");
            JSONArray topics = result.getJSONArray("topics");
            String data = result.getString("data");
            String topics0 = topics.getString(0);

            // System.out.println(result.toString());

            // TODO: YGIO Convert Event
            if (topics.length() == 4 && ConvertTopic.equalsIgnoreCase(topics0)) {

                String convertType = Numeric.toBigInt(topics.getString(1)).toString();

                String account = FunctionReturnDecoder.decodeAddress(topics.getString(2));

                String amount = Numeric.toBigInt(topics.getString(3)).toString();

                String blockTime = Numeric.toBigInt(data).toString();

                JSONObject trasferObject = new JSONObject();

                // 向 JSON 对象添加元素
                trasferObject.put("transactionHash", transactionHash);
                trasferObject.put("contract", tokenAddress);
                trasferObject.put("convertType", convertType);
                trasferObject.put("account", account);
                trasferObject.put("amount", amount);
                trasferObject.put("blockTime", blockTime);

                System.out.println(trasferObject.toString());

            }

        }
    }

}