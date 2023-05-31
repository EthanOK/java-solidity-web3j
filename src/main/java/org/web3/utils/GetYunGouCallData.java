package org.web3.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.web3j.model.YunGouInterface;
import org.web3j.model.YunGouInterface.BasicOrderParameters;
import org.web3j.model.YunGouInterface.BasicOrder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import io.github.cdimascio.dotenv.Dotenv;

public class GetYunGouCallData {

    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");
    static String YunGouAddress = "0x54dbd3ddc55af51947b115cb3d511214eca5d58f";

    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j
                .build(new HttpService(RPC));
        // System.out.println(web3j);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        YunGouInterface YunGou = YunGouInterface.load(YunGouAddress, web3j,
                new ReadonlyTransactionManager(web3j, null), contractGasProvider);

        String receiver = "0x53188e798f2657576c9de8905478f46ac2f24b67";
        BigInteger weiValue = new BigInteger("0");
        BasicOrder basicOrder_7 = getBasicOrder_7();
        // TODO: get excuteWithETH calldata
        String calldata_excuteWithETH = YunGou.excuteWithETH(basicOrder_7, receiver, weiValue).encodeFunctionCall();
        System.out.println(calldata_excuteWithETH);

        // TODO: get batchExcuteWithETH calldata
        List<BasicOrder> orders = new ArrayList<>();
        orders.add(basicOrder_7);
        BasicOrder basicOrder_8 = getBasicOrder_8();
        orders.add(basicOrder_8);
        String calldata_batchExcuteWithETH = YunGou.batchExcuteWithETH(orders, receiver, weiValue)
                .encodeFunctionCall();
        System.out.println(calldata_batchExcuteWithETH);
    }

    public static BasicOrder getBasicOrder_7() {
        BigInteger orderType = new BigInteger("0");
        String offerer = "0x6278a1e803a76796a3a1f7f6344fe874ebfe94b2";
        String offerToken = "0xeaafcc17f28afe5cda5b3f76770efb7ef162d20b";
        BigInteger offerTokenId = new BigInteger("7");
        BigInteger unitPrice = new BigInteger("10000000000000000");
        BigInteger sellAmount = new BigInteger("1");
        BigInteger startTime = new BigInteger("1684304543");
        BigInteger endTime = new BigInteger("1686896535");
        String paymentToken = "0x0000000000000000000000000000000000000000";
        BigInteger paymentTokenId = new BigInteger("0");
        BigInteger salt = new BigInteger("7");
        BigInteger royaltyFee = new BigInteger("250000000000000");
        BigInteger platformFee = new BigInteger("250000000000000");
        BigInteger afterTaxPrice = new BigInteger("9500000000000000");

        BasicOrderParameters basicOrderParameters = new BasicOrderParameters(orderType, offerer, offerToken,
                offerTokenId, unitPrice,
                sellAmount, startTime, endTime, paymentToken, paymentTokenId, salt, royaltyFee, platformFee,
                afterTaxPrice);
        String orderSignatureHex = "0xba94ee1982370efdfec73ad8ceee44f7d259e284d49fcb17cdf6d5dcf31baaad73b84189e5175b5d15c9c2d55ed8e9ad332b7f897ac1f17e1f1752eac9df51e51c";
        byte[] orderSignature = Numeric.hexStringToByteArray(orderSignatureHex);

        BigInteger buyAmount = new BigInteger("1");

        BigInteger totalRoyaltyFee = new BigInteger("250000000000000");

        BigInteger totalPlatformFee = new BigInteger("250000000000000");

        BigInteger totalAfterTaxIncome = new BigInteger("9500000000000000");

        BigInteger totalPayment = new BigInteger("10000000000000000");

        BigInteger expiryDate = new BigInteger("1686896535");

        String systemSignatureHex = "0xa6cc9a620a545a39a2cc384730c3d548c9235ca3ff6cbe1fe562aa065ab944c479c9150ed2b08830d3525f1a350971cd7c400f05c360a3e7ae78fba1de7b28ee1b";
        byte[] systemSignature = Numeric.hexStringToByteArray(systemSignatureHex);

        BasicOrder basicOrder = new BasicOrder(basicOrderParameters, orderSignature, buyAmount, totalRoyaltyFee,
                totalPlatformFee,
                totalAfterTaxIncome, totalPayment, expiryDate, systemSignature);
        return basicOrder;

    }

    public static BasicOrder getBasicOrder_8() {
        BigInteger orderType = new BigInteger("0");
        String offerer = "0x6278a1e803a76796a3a1f7f6344fe874ebfe94b2";
        String offerToken = "0xeaafcc17f28afe5cda5b3f76770efb7ef162d20b";
        BigInteger offerTokenId = new BigInteger("8");
        BigInteger unitPrice = new BigInteger("10000000000000000");
        BigInteger sellAmount = new BigInteger("1");
        BigInteger startTime = new BigInteger("1684304543");
        BigInteger endTime = new BigInteger("1686896535");
        String paymentToken = "0x0000000000000000000000000000000000000000";
        BigInteger paymentTokenId = new BigInteger("0");
        BigInteger salt = new BigInteger("8");
        BigInteger royaltyFee = new BigInteger("250000000000000");
        BigInteger platformFee = new BigInteger("250000000000000");
        BigInteger afterTaxPrice = new BigInteger("9500000000000000");

        BasicOrderParameters basicOrderParameters = new BasicOrderParameters(orderType, offerer, offerToken,
                offerTokenId, unitPrice,
                sellAmount, startTime, endTime, paymentToken, paymentTokenId, salt, royaltyFee, platformFee,
                afterTaxPrice);
        String orderSignatureHex = "0x3fd254a469286adee0ed2fa95e55de1567263cc5076992c55dff5b6c151631ac47a7ad1a6aa47113aac7ceb1b612d6bf2186225a1ffa1accf275cb57b3b6e7971c";
        byte[] orderSignature = Numeric.hexStringToByteArray(orderSignatureHex);

        BigInteger buyAmount = new BigInteger("1");

        BigInteger totalRoyaltyFee = new BigInteger("250000000000000");

        BigInteger totalPlatformFee = new BigInteger("250000000000000");

        BigInteger totalAfterTaxIncome = new BigInteger("9500000000000000");

        BigInteger totalPayment = new BigInteger("10000000000000000");

        BigInteger expiryDate = new BigInteger("1686896535");

        String systemSignatureHex = "0xfdab6293ecc0e6fb24f109f2008a02c8c59fb39fbf1e7b097581e7d45861346344edec417c3716c760b90c47b38dbab4892173cacfdd01341abd82b815ba94761c";
        byte[] systemSignature = Numeric.hexStringToByteArray(systemSignatureHex);

        BasicOrder basicOrder = new BasicOrder(basicOrderParameters, orderSignature, buyAmount, totalRoyaltyFee,
                totalPlatformFee,
                totalAfterTaxIncome, totalPayment, expiryDate, systemSignature);
        return basicOrder;
    }

}
