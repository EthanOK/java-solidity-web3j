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
        String txhash = "0xb8c6ecdd8da70e8bbbc8a7731e38601667c3b4a66862d7b9200161225f508a34";

        Web3j web3j = Web3j.build(new HttpService(ANKR_HTTP_BSC_T));

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(txhash).send();

        TransactionReceipt receipt = transactionReceipt.getResult();

        if (receipt == null) {
            System.out.println("null");
            return;
        }

        if (receipt.getStatus().equals("0x1")) {
            System.out.println("success");
        } else {
            System.out.println("failure");

        }

        System.out.println(receipt.isStatusOK());

    }
}
