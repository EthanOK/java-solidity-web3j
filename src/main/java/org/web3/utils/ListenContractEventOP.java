package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import org.json.JSONObject;
import org.web3j.model.NFTExchangeLog;
import org.web3j.protocol.Web3j;

import org.web3j.protocol.core.DefaultBlockParameterName;

import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class ListenContractEventOP {
        static Dotenv dotenv = Dotenv.load();
        static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");

        public static void main(String[] args) throws IOException {
                Web3j web3j = Web3j
                                .build(new HttpService(RPC));
                EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
                                DefaultBlockParameterName.LATEST, "0x71eE06999F6D5f66AcA3c12e45656362fD9D031f");

                // function 1: only get data no listen
                // EthLog ethLog = web3j.ethGetLogs(filter).send();
                // System.out.println(ethLog.getLogs());

                // function 2:
                web3j.ethLogFlowable(filter).subscribe(event -> {
                        JSONObject json = new JSONObject(event);
                        BigInteger blockNumber = json.getBigInteger("blockNumber");
                        // 记录最新的区块，下次从记录的区块开始监听，有效减少查询重复数据
                        System.out.println("BlockNumber:" + blockNumber);
                        System.out.println(json);
                });

        }

}