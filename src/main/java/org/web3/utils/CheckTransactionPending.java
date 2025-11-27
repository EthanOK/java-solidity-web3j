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

    enum TxStatus {
        NOTFOUND("NotFound"),
        PENDING("Pending"),
        SUCCESS("Success"),
        FAILURE("Failure");

        private final String label;

        TxStatus(String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }
    }

    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_SEPOLIA_URL");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String txhash_Success = "0xa20268f82eecc04ecf817e832ab40a39c81d35700cf2988609133ee73ad819b9";
        String txhash_Failure = "0xf09d2448e9331fef65ebe9edc4d1d01a471103ff9472a2c9ca93c2be1efafa64";
        String txhash_Pending = "0xa295391665f9bb218156e45a5e05a587917e722da165599725c87898cd6c757f";
        String txhash_NotFound = "0xa20268f82eecc04ecf817e832ab40a39c81d35700cf2988609133ee73ad819b6";

        String[] txhashes = { txhash_Success, txhash_Failure, txhash_Pending, txhash_NotFound };

        Web3j web3j = Web3j.build(new HttpService(RPC));
        for (String txhash : txhashes) {
            EthTransaction transactionResponse = web3j.ethGetTransactionByHash(txhash).sendAsync().get();
            Transaction transaction = transactionResponse.getResult();
            TxStatus status = checkTransactionStatus(web3j, txhash, transaction);
            System.out.println(txhash + " " + status.label());
        }

    }

    public static TxStatus checkTransactionStatus(Web3j web3j, String txhash, Transaction transaction)
            throws InterruptedException, ExecutionException {

        if (transaction == null) {
            return TxStatus.NOTFOUND;
        }

        if (transaction.getBlockHash() == null) {
            return TxStatus.PENDING;
        }

        EthGetTransactionReceipt transactionReceipt = web3j
                .ethGetTransactionReceipt(txhash)
                .sendAsync().get();
        TransactionReceipt receipt = transactionReceipt.getResult();

        if (receipt == null || receipt.getStatus() == null) {
            return TxStatus.PENDING;
        }

        if ("0x1".equals(receipt.getStatus())) {
            return TxStatus.SUCCESS;
        }
        if ("0x0".equals(receipt.getStatus())) {
            return TxStatus.FAILURE;
        }
        return TxStatus.FAILURE;
    }

}