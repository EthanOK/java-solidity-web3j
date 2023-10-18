package org.web3.utils;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

public class GetTxStatusByHash {
    static String ANKR_HTTP_BSC = "https://rpc.ankr.com/bsc";

    static String ANKR_HTTP_BSC_T = "https://rpc.ankr.com/bsc_testnet_chapel";

    public static void main(String[] args) throws IOException {
        String txhash = "0xfbce55d806a96a65be3c989e8af9bc9cc1e72e7476250fe11badda499b5e9f85";

        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC_T));

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(txhash).send();

        TransactionReceipt receipt = transactionReceipt.getResult();

        if (receipt.getStatus().equals("0x1")) {
            System.out.println("success");
        } else {
            System.out.println("failure");

        }

    }
}
