package org.web3.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import io.github.cdimascio.dotenv.Dotenv;

public class GetEthBalanceOfAccount {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ETH_MAIN_URL");

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        EthBlockNumber number = web3j.ethBlockNumber().send();

        System.out.println(number.getBlockNumber());

        EthGetBalance ethGetBalance = web3j.ethGetBalance("0xC8AF6822A556f7BEbA1C8894bc59777302d8E7ba",
                DefaultBlockParameterName.LATEST).send();
        System.out.println(ethGetBalance.getBalance());
        BigDecimal res = Convert.fromWei(ethGetBalance.getBalance().toString(), Unit.ETHER);
        System.out.println(res);

        EthGasPrice gasPrice = web3j.ethGasPrice().send();
        BigInteger gasPriceWei = gasPrice.getGasPrice();
        BigDecimal gasPriceGwei = Convert.fromWei(gasPriceWei.toString(), Convert.Unit.GWEI);
        System.out.println("当前 gas 价格：" + gasPriceGwei + " Gwei");

    }

}
