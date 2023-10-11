package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class GetCurrentBlockNumber {
    static Dotenv dotenv = Dotenv.load();

    // static String ANKR_HTTP_BSC = dotenv.get("ANKR_HTTP_BSC");
    static String ANKR_HTTP_BSC = "https://rpc.ankr.com/bsc";

    static String ANKR_HTTP_BSC_T = "https://rpc.ankr.com/bsc_testnet_chapel";

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            System.out.println(getLastBlockNumber());
            Thread.sleep(6000);

        }

    }

    private static BigInteger getLastBlockNumber() throws IOException {
        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC));

        // EthBlock.Block latestBlock =
        // web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
        // .send().getBlock();
        // System.out.println("Block: " + latestBlock.getNumber());

        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();

        BigInteger number = blockNumber.getBlockNumber();

        return number;
    }

}
