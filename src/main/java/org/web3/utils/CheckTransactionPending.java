package org.web3.utils;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class CheckTransactionPending {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_SEPOLIA_URL");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String txhash_Success = "0xa20268f82eecc04ecf817e832ab40a39c81d35700cf2988609133ee73ad819b9";
        String txhash_Failure = "0xf09d2448e9331fef65ebe9edc4d1d01a471103ff9472a2c9ca93c2be1efafa64";
        String txhash_Pending = "0xcb918ddfdd1e33314b4125c20e83bdf843c4527d0c3c690f311d98793c6083f3";
        String txhash_NotFound = "0xa20268f82eecc04ecf817e832ab40a39c81d35700cf2988609133ee73ad819b6";

        String[] txhashes = { txhash_Success, txhash_Failure, txhash_Pending, txhash_NotFound };

        Web3j web3j = Web3j.build(new HttpService(RPC));
        for (String txhash : txhashes) {
            checkTransactionPending(web3j, txhash);
        }

    }

    public static void checkTransactionPending(Web3j web3j, String txhash)
            throws InterruptedException, ExecutionException {

        EthTransaction transactionReceipt = web3j
                .ethGetTransactionByHash(txhash)
                .sendAsync().get();
        Transaction transaction = transactionReceipt.getResult();
        if (transaction == null) {
            // No Found
            System.out.println("Transaction Not Found");
        } else if (transaction.getBlockHash() != null) {
            // Blocked
            System.out.print("Blocked in block: " + transaction.getBlockNumber() + "; ");
            // System.out.println(new JSONObject(transaction).toString(2));
            boolean status = checkTransactionStatus(web3j, txhash);
            if (status) {
                System.out.println("Transaction Success");
            } else {
                System.out.println("Transaction Failure");
            }

        } else {
            // Pending
            System.out.println("Transaction Pending");
            // System.out.println(new JSONObject(transaction).toString(2));
        }
    }

    public static boolean checkTransactionStatus(Web3j web3j, String txhash)
            throws InterruptedException, ExecutionException {
        EthGetTransactionReceipt transactionReceipt = web3j
                .ethGetTransactionReceipt(txhash)
                .sendAsync().get();
        TransactionReceipt receipt = transactionReceipt.getResult();
        if (receipt.getStatus().equals("0x1")) {
            return true;
        } else {
            return false;
        }
    }
}
