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
        String opensea = "0x00000000000000ADc04C56Bf30aC9d3c0aAF14dC";
        String yungou = "0xb0E3773e3E02d0A1653F90345Bc8889fC820E230";
        Disposable subscription = web3j.pendingTransactionFlowable()
                .subscribe(tx -> {
                    String to = tx.getTo();
                    // System.out.println(tx.getHash());
                    // System.out.println(tx.getFrom());
                    // System.out.println(tx.getTo());
                    // System.out.println("````````````````````");
                    // System.out.println(tx.getInput());
                    if (to != null) {
                        if (to.equalsIgnoreCase(opensea)) {
                            System.out.println("Opensea pending transaction: " + tx.getHash());
                            System.out.println(to);
                        } else if (to.equalsIgnoreCase(yungou)) {
                            System.out.println("YunGou pending transaction: " + tx.getHash());
                            System.out.println(to);
                        }
                    } else {
                        System.out.println(to);
                    }

                });

        // subscription.dispose();

    }
}
