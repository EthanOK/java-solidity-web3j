package org.web3.utils;

import java.math.BigInteger;

import org.json.JSONObject;
import org.web3j.model.NFTTransfer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import io.github.cdimascio.dotenv.Dotenv;

public class ListenTransferEvent {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");

    public static void main(String[] args) {
        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        NFTTransfer contract = NFTTransfer.load("0x97f236E644db7Be9B8308525e6506E4B3304dA7B", web3j,
                new ReadonlyTransactionManager(web3j, null), contractGasProvider);
        DefaultBlockParameter startBlock = new DefaultBlockParameterNumber(new BigInteger("8899100"));
        ;
        DefaultBlockParameter endBlock = DefaultBlockParameterName.LATEST;
        contract.transferEventFlowable(startBlock, endBlock)
                .subscribe(event -> {

                    JSONObject json = new JSONObject(event.log);
                    BigInteger blockNumber = json.getBigInteger("blockNumber");
                    System.out.println("BlockNumber:" + blockNumber);
                    System.out.println("From:" + event.from + " To:" + event.to + " TokenId:" + event.tokenId);

                    // event.log
                    // {
                    // removed=false, logIndex='0x79', transactionIndex='0x3b',
                    // transactionHash='0x77d9e768e00f80f77981d1744bf3948cdc0e60f1e177310bca520e465c295cc3',
                    // blockHash='0x2653293208c1c384b89c6e57e289987235f95d720d7d610c33040c42ac259200',
                    // blockNumber='0x87ca33', address='0x97f236e644db7be9b8308525e6506e4b3304da7b',
                    // data='0x',
                    // type='null',
                    // topics=[0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef,
                    // 0x0000000000000000000000006278a1e803a76796a3a1f7f6344fe874ebfe94b2,
                    // 0x00000000000000000000000053188e798f2657576c9de8905478f46ac2f24b67,
                    // 0x0000000000000000000000000000000000000000000000000000000000000064]
                    // }
                });
    }

}