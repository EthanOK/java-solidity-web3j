package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.model.NFTExchangeLog;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;

import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import io.github.cdimascio.dotenv.Dotenv;

public class GetAllTransferEvent {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");
    static String TransferTopic = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

    // 获取指定区块号的 区块哈希
    static BigInteger startBlockNumber = new BigInteger("10320093");

    public static void main(String[] args) throws IOException {
        Web3j web3j = Web3j
                .build(new HttpService(RPC));

        // 死循环 6s执行一次
        while (true) {
            try {
                GetAllTransferEventByBlockNumber(web3j, startBlockNumber.toString());
                Thread.sleep(6000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void GetAllTransferEventByBlockNumber(Web3j web3j, String blockNumber) throws IOException {
        BigInteger blockNumber_ = new BigInteger(blockNumber);
        EthBlock.Block latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber_), false)
                .send().getBlock();
        if (latestBlock == null) {
            System.out.println(blockNumber_.toString() + " 区块还没产生");
            return;
        }
        System.out.println("BlockNumber: " + latestBlock.getNumber());

        System.out.println("BlockHash: " + latestBlock.getHash());
        String blockHash = latestBlock.getHash();
        EthFilter filter = new EthFilter(blockHash)
                .addOptionalTopics(TransferTopic);

        // function 1: only get data no listen
        EthLog ethLog = web3j.ethGetLogs(filter).send();
        List<LogResult> logs = ethLog.getLogs();
        if (logs.size() > 0) {
            handleLogsResult(logs);
        }
        startBlockNumber = blockNumber_.add(BigInteger.ONE);
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

            // TODO: NFT Transfer Event
            if (topics.length() == 4 && TransferTopic.equalsIgnoreCase(topics0)) {

                String from = topics.getString(1);
                String to = topics.getString(2);
                String fromAddress = FunctionReturnDecoder.decodeAddress(from);
                String toAddress = FunctionReturnDecoder.decodeAddress(to);
                String tokenIdHex = topics.getString(3);
                BigInteger tokenId = Numeric.toBigInt(tokenIdHex);

                JSONObject trasferObject = new JSONObject();

                // 向 JSON 对象添加元素
                trasferObject.put("txHash", transactionHash);
                trasferObject.put("token", tokenAddress);
                trasferObject.put("tokenId", tokenId);
                trasferObject.put("from", fromAddress);
                trasferObject.put("to", toAddress);

                System.out.println(trasferObject.toString());

            }
            // TODO: Token(ERC20) Transfer Event
            else if (topics.length() == 3 && TransferTopic.equalsIgnoreCase(topics0)) {
                String from = topics.getString(1);
                String to = topics.getString(2);
                String fromAddress = FunctionReturnDecoder.decodeAddress(from);
                String toAddress = FunctionReturnDecoder.decodeAddress(to);

                BigInteger value = Numeric.toBigInt(data);

                JSONObject trasferObject = new JSONObject();

                // 向 JSON 对象添加元素
                trasferObject.put("txHash", transactionHash);
                trasferObject.put("token", tokenAddress);

                trasferObject.put("from", fromAddress);
                trasferObject.put("to", toAddress);
                trasferObject.put("value", value);

                System.out.println(trasferObject.toString());

            }

        }
    }

}