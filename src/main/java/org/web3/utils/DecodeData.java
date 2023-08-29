package org.web3.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

public class DecodeData {
    static String data = "0xa4c7e37429e405f6f9460dc71e4acdcd5344a35786054562cb1580eda020c91e00000000000000000000000000000000000000000000000000000000000005300000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000002386f26fc100000000000000000000000000000000000000000000000000000001c6bf526340000000000000000000000000000000000000000000000000000000e35fa931a000";

    public static String weiToEth(BigInteger wei) {
        String eth = Convert.fromWei(wei.toString(), Convert.Unit.ETHER).toString();
        return eth;

    }

    public static void main(String[] args) {
        decodeHexData(data);
    }

    public static void decodeHexData(String dataHex) {
        List<TypeReference<Type>> outputParameters = new ArrayList<>(6);

        outputParameters.add((TypeReference) new TypeReference<Bytes32>() {
        });
        outputParameters.add((TypeReference) new TypeReference<Uint256>() {
        });
        outputParameters.add((TypeReference) new TypeReference<Uint256>() {
        });
        outputParameters.add((TypeReference) new TypeReference<Uint256>() {
        });
        outputParameters.add((TypeReference) new TypeReference<Uint256>() {
        });
        outputParameters.add((TypeReference) new TypeReference<Uint256>() {
        });

        List<Type> decodedString = FunctionReturnDecoder.decode(
                dataHex, outputParameters);
        byte[] orderHash_ = (byte[]) decodedString.get(0).getValue();
        // byte[] to hexstring
        String orderHash = Numeric.toHexString(orderHash_);
        BigInteger offerTokenId = (BigInteger) decodedString.get(1).getValue();
        BigInteger buyAmount = (BigInteger) decodedString.get(2).getValue();
        BigInteger totalPayment = (BigInteger) decodedString.get(3).getValue();
        BigInteger totalRoyaltyFee = (BigInteger) decodedString.get(4).getValue();
        BigInteger totalPlatformFee = (BigInteger) decodedString.get(5).getValue();
        System.out.println(orderHash);
        System.out.println(offerTokenId);
        System.out.println(buyAmount);
        System.out.println("totalPayment:" + weiToEth(totalPayment) + "eth");
        System.out.println("totalRoyaltyFee:" + weiToEth(totalRoyaltyFee) + "eth");
        System.out.println("totalPlatformFee:" + weiToEth(totalPlatformFee) + "eth");
    }

}
