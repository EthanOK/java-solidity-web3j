package org.web3.utils;

import java.math.BigInteger;

import org.web3j.model.NFTTransfer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import io.github.cdimascio.dotenv.Dotenv;

public class ListenContractEvent {
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
                    System.out.println("From:" + event.from + " To:" + event.to + " TokenId:" + event.tokenId);
                });
    }

}