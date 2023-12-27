package org.web3.utils;
import org.sol4k.Base58;
import org.sol4k.PublicKey;
public class VerifySolanaSigenature {

    public static boolean verifySolanaSignature(String message, String signature, String walletAddress) {

        byte[] messageBytes = message.getBytes();
        PublicKey publicKey = new PublicKey(walletAddress);
        byte[] signatureBytes = Base58.decode(signature);
        return publicKey.verify(signatureBytes, messageBytes);

    }



    public static void main(String[] args) {
        // 示例消息、签名和公钥
        String message = "Welcome to ethan-yungou.vercel.app!";
        String signature = "ASkzqDLqe1AsxiWMELTb2b3DHdtXqpWzoBcxRq1Wkit4ZnjkwKJZPpeonag94CFjcF2oCHWXTZYwVTEbJQ1dChs";
        String walletAddress = "AQAMLqdN3LSvaHx5tCVeWZWDRTGqL7QuvNgojCb3pS6Z";

        // 执行数字签名验证
        boolean isValid = verifySolanaSignature(message, signature, walletAddress);

        System.out.println("签名验证结果："+isValid);

        // 输出验证结果
        if (isValid) {
            System.out.println("Signature is valid.");
        } else {
            System.out.println("Signature is NOT valid.");
        }
    }
}

//<dependency>
//<groupId>org.sol4k</groupId>
//<artifactId>sol4k</artifactId>
//<version>0.4.1</version>
//</dependency>
