package org.web3.utils;

import java.math.BigInteger;

import org.json.JSONObject;
import org.web3j.model.NFTExchangeLog;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import io.github.cdimascio.dotenv.Dotenv;

public class ListenNFTExchangeEvent {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");

    public static void main(String[] args) {
        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        NFTExchangeLog contract = NFTExchangeLog.load("0x5D0C8b801d2Fd1dEEBB5aDfA89a8609becD01D83", web3j,
                new ReadonlyTransactionManager(web3j, null), contractGasProvider);
        // DefaultBlockParameter startBlock = new DefaultBlockParameterNumber(new
        // BigInteger("8899100"));
        DefaultBlockParameter startBlock = DefaultBlockParameterName.EARLIEST;
        DefaultBlockParameter endBlock = DefaultBlockParameterName.LATEST;
        contract.exchangeEventFlowable(startBlock, endBlock)
                .subscribe(event -> {
                    JSONObject json = new JSONObject(event.log);
                    BigInteger blockNumber = json.getBigInteger("blockNumber");
                    // 记录最新的区块，下次从记录的区块开始监听，有效减少查询重复数据
                    System.out.println("BlockNumber:" + blockNumber);
                    // System.out.println(event.log);
                    System.out.println(
                            "sellToken:" + event.sellToken + " sellTokenId:" + event.sellTokenId + " sellAmount:"
                                    + event.sellAmount + " unitPrice:" + event.unitPrice + " seller:"
                                    + event.seller + " buyToken:" + event.buyToken + " buyTokenId:"
                                    + event.buyTokenId + " buyer:" + event.buyer + " amount:"
                                    + event.amount + " payPrice:"
                                    + event.payPrice + " royaltyFee:"
                                    + event.royaltyFee);

                });
    }

}