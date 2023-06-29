package org.web3.utils;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

import io.github.cdimascio.dotenv.Dotenv;

public class YunGouSystemSignature {
    static Dotenv dotenv = Dotenv.load();
    static String privatekey = dotenv.get("private_key_operator");

    public static void main(String[] args) throws SignatureException {

        String privateKeyHex = "0x" + privatekey;

        // messageHashHex = keccak256(abi.encode(address, uint256))

        String messageHashHex = getHashOfEncodeSystemMessage();

        System.out.println("messageHashHex: " + messageHashHex);

        String signerAddress = getAddressFromPrivateKey(privateKeyHex);

        System.out.println("signer Address: " + signerAddress);

        String signatureString = getEthereumSignature(messageHashHex, privateKeyHex);
        System.out.println("signatureString: " + signatureString);

        boolean verifyResult = verifyEthereumSignature(messageHashHex, signatureString, signerAddress);

        System.out.println("verifyResult: " + verifyResult);
    }

    public static SignatureData convertSignatureToData(String signatureString) {
        // 将签名字符串拆分为字节数组
        byte[] signatureBytes = Numeric.hexStringToByteArray(signatureString);
        // 将签名字节数组解析为SignatureData对象
        byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);
        byte v = signatureBytes[64];

        return new SignatureData(v, r, s);
    }

    public static boolean verifyEthereumSignature(String messageHashHex, String signatureHashHex, String signer)
            throws SignatureException {
        byte[] messageHash = Numeric.hexStringToByteArray(messageHashHex);
        Sign.SignatureData signatureData = convertSignatureToData(signatureHashHex);
        BigInteger publicKey = Sign.signedPrefixedMessageToKey(messageHash, signatureData);

        if (publicKey != null) {
            String address = "0x" + Keys.getAddress(publicKey);
            return signer.equalsIgnoreCase(address);
        }
        return false;
    }

    public static String getEthereumSignature(String messageHashHex,
            String privateKeyHex) {
        byte[] messageHash = Numeric.hexStringToByteArray(messageHashHex);
        Credentials credentials = Credentials.create(privateKeyHex);
        Sign.SignatureData signatureData = Sign.signPrefixedMessage(messageHash, credentials.getEcKeyPair());
        String signature = Numeric.toHexString(signatureData.getR())
                + Numeric.toHexStringNoPrefix(signatureData.getS())
                + Numeric.toHexStringNoPrefix(signatureData.getV());
        return signature;
    }

    public static String getAddressFromPrivateKey(String privateKeyHex) {
        Credentials credentials = Credentials.create(privateKeyHex);
        String address = credentials.getAddress();
        return address;
    }

    public static String getHashOfEncodeSystemMessage() {

        // orderSignature,
        // buyAmount,
        // totalRoyaltyFee,
        // totalPlatformFee,
        // totalAfterTaxIncome,
        // totalPayment,
        // expiryDate
        String orderSignatureHex = "0x5fb2a6a71245a523fb2b1bdc339d203f9c085fb20fbb07b1507749505b4cd3934655f7ed328077c788f89caad077b1d87928b873eed7271614e6a236f0a9be4b1b";
        DynamicBytes orderSignature = new DynamicBytes(Numeric.hexStringToByteArray(orderSignatureHex));
        Uint256 buyAmount = new Uint256(new BigInteger("1"));
        Uint256 totalRoyaltyFee = new Uint256(new BigInteger("250000000000000"));
        Uint256 totalPlatformFee = new Uint256(new BigInteger("250000000000000"));
        Uint256 totalAfterTaxIncome = new Uint256(new BigInteger("9500000000000000"));
        Uint256 totalPayment = new Uint256(new BigInteger("10000000000000000"));
        Uint256 expiryDate = new Uint256(new BigInteger("1687996400"));

        List<Type> lists = Arrays.asList(
                orderSignature,
                buyAmount,
                totalRoyaltyFee,
                totalPlatformFee,
                totalAfterTaxIncome,
                totalPayment,
                expiryDate);
        String encodeData = FunctionEncoder.encodeConstructor(lists);
        String encodeDataHex = "0x" + encodeData;

        String encodeDataHash = Hash.sha3(encodeDataHex);
        // System.out.println(encodeDataHash);
        return encodeDataHash;
    }
}
