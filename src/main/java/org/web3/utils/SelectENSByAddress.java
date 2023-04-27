package org.web3.utils;

import org.web3j.crypto.WalletUtils;
import org.web3j.crypto.Keys;
import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import io.github.cdimascio.dotenv.Dotenv;

public class SelectENSByAddress {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ETH_MAIN_URL");
    static Web3j web3j = Web3j
            .build(new HttpService(RPC));
    static private EnsResolver ensResolver = new EnsResolver(web3j);

    public static String getENSNameByAddress(String address) throws Exception {

        boolean result = WalletUtils.isValidAddress(address);

        if (result) {
            String resolvedAddress = Keys.toChecksumAddress(address);
            try {
                String ensName = ensResolver.reverseResolve(resolvedAddress);
                return ensName;
            } catch (Exception e) {
                return "null";
            }
        } else {
            throw new Exception("输入地址无效");
        }

    }

    public static String getAddressByENSName(String ensName) {
        String address = ensResolver.resolve(ensName);
        return address;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("````````````````````````````");
        System.out.println(getENSNameByAddress("0xdbfd76af2157dc15ee4e57f3f942bb45ba84af24"));

        System.out.println(getAddressByENSName("btcwu.eth"));
    }

}
