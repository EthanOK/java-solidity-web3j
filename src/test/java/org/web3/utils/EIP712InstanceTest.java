package org.web3.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.*;

public class EIP712InstanceTest {

    @Test
    @DisplayName("测试EIP712签名")
    public void testEIP712Sign() throws Exception {
        String privateKey = "0x1234567890123456789012345678901234567890123456789012345678901234"; // 你的私钥 0x 开头

        // EIP712 Domain（按你合约实际填写）
        Map<String, Object> domain = new LinkedHashMap<>();
        domain.put("name", "USD₮0");
        domain.put("version", "1");
        domain.put("chainId", "196");
        domain.put("verifyingContract", "0x779ded0c9e1022225f8e0630b35a9b54be713736"); // USDT

        // types：TransferWithAuthorization
        Map<String, List<Map<String, String>>> types = new LinkedHashMap<>();
        types.put("TransferWithAuthorization", Arrays.asList(
                Map.of("name", "from", "type", "address"),
                Map.of("name", "to", "type", "address"),
                Map.of("name", "value", "type", "uint256"),
                Map.of("name", "validAfter", "type", "uint256"),
                Map.of("name", "validBefore", "type", "uint256"),
                Map.of("name", "nonce", "type", "bytes32")));

        // message：注意 uint256 你可以传 Number 或 String（你工具里会统一转成 string）
        Map<String, Object> message = new LinkedHashMap<>();
        message.put("from", "0x19E7E376E7C213B7E7e7e46cc70A5dD086DAff2A");
        message.put("to", "0x0dedc3c5e15bee45166924ea5b02f54a35b1f9c6");
        message.put("value", "500"); // 例如 1 USDC(6 decimals)
        message.put("validAfter", "0");
        message.put("validBefore", "1710000000"); // unix seconds
        message.put("nonce", "0x0000000000000000000000000000000000000000000000000000000000000001");

        String sig = EIP712Instance.signTypedData(
                privateKey,
                domain,
                types,
                message,
                "TransferWithAuthorization");

        System.out.println("signature: " + sig); // 0x + r + s + v

        assertTrue(sig.equals(
                "0x7f150d6cbfb1655ef49942f48014513e2064713343f07dc8dc5bdab2bfd9ed81065e6b45181acc721e4deb571143407cfd2bf98816ad99060f9caf669ee183341c"),
                "签名不正确");
    }
}