package org.web3.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;
import io.reactivex.disposables.Disposable;

public class ListenPendingContract {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");

    public static void main(String[] args) {

        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        String contract = "0x71eE06999F6D5f66AcA3c12e45656362fD9D031f";
        Disposable subscription = web3j.pendingTransactionFlowable()
                .subscribe(tx -> {
                    String to = tx.getTo();
                    System.out.println(tx.getHash());
                    System.out.println(tx.getFrom());
                    System.out.println(tx.getTo());

                    if (to != null && tx.getTo().toLowerCase() == contract.toLowerCase()) {
                        System.out.println("1111111111111111111111111");
                        System.out.println("New pending transaction: " + tx.getHash());
                        System.out.println(to);
                        System.out.println(contract);
                    }

                });

        // subscription.dispose();

    }
}
