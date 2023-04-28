package org.web3.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;
import io.reactivex.disposables.Disposable;

public class ListenPendingContract {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");

    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        Disposable subscription = web3j.pendingTransactionFlowable().subscribe(tx -> {
            System.out.println(tx.getHash());
            System.out.println(tx.getFrom());
            System.out.println(tx.getTo());
        });

    }
}
