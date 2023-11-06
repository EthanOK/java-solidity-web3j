package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Numeric;
import org.web3j.protocol.core.methods.response.EthGetStorageAt;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class GetStorageAtSlot {
    static Dotenv dotenv = Dotenv.load();

    static String ANKR_HTTP_BSC = dotenv.get("ANKR_HTTP_BSC");

    static String ETH_MAIN_URL = dotenv.get("ETH_MAIN_URL");

    static Web3j web3j_ETH = Web3j.build(new HttpService(ETH_MAIN_URL));

    static Web3j web3j_BSC = Web3j.build(new HttpService(ANKR_HTTP_BSC));

    public static void main(String[] args) throws IOException {
        int[] totalSupplys = getYGMETotalSupply();

        int totalSupply_E = totalSupplys[0];

        int totalSupply_B = totalSupplys[1];

        System.out.println(totalSupply_E);
        System.out.println(totalSupply_B);

    }

    public static String getData_ETH(String contract, String slot) throws IOException {
        BigInteger slot_ = new BigInteger(slot);
        EthGetStorageAt ethGetStorageAt = web3j_ETH.ethGetStorageAt(contract, slot_, DefaultBlockParameterName.LATEST)
                .send();
        return ethGetStorageAt.getData();

    }

    public static String getData_BSC(String contract, String slot) throws IOException {
        BigInteger slot_ = new BigInteger(slot);
        EthGetStorageAt ethGetStorageAt = web3j_BSC.ethGetStorageAt(contract, slot_, DefaultBlockParameterName.LATEST)
                .send();
        return ethGetStorageAt.getData();

    }

    public static int[] getYGMETotalSupply() throws IOException {

        String contract_E = "0x1b489201D974D37DDd2FaF6756106a7651914A63";
        String contract_B = "0xe88e04e739eb73978e76b6a20a86643f2a0e364a";

        String total_E = getData_ETH(contract_E, "7");

        String total_B = getData_BSC(contract_B, "7");

        BigInteger total_E_ = Numeric.toBigInt(total_E);

        BigInteger total_B_ = Numeric.toBigInt(total_B);

        int totalSupply_E = total_E_.intValue() - 1;
        int totalSupply_B = total_B_.intValue();

        return new int[] { totalSupply_E, totalSupply_B };
    }

}
