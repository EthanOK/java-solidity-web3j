package org.web3.utils;

import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class GetENS2 {
    static Dotenv dotenv = Dotenv.load();
    static String URL = dotenv.get("GET_ENS_URL");
    static Web3j web3j = Web3j
            .build(new HttpService(URL));
    static EnsResolver ensResolver = new EnsResolver(web3j);

    public static void main(String[] args) {
        // 调用一次 请求RPC5次
        String name = ensResolver.reverseResolve("0xb5425ebed48d8c859a19a34463b6df9437974d1b");
        System.out.println(name);
    }

}
