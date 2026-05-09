package org.web3.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

import org.junit.jupiter.api.*;

public class X402_OKX_CustomTest {

  // 固定类型（强类型）版本：不用 JSON 解析也能直接取字段
  record X402Data(int x402Version, X402_OKX_Custom.X402Resource resource,
      List<X402_OKX_Custom.X402Accepted> accepts) {
  }

  // 当任意行情 API 付费接口触发 x402 付款信息时，你将收到如下返回内容：
  static final String X402_DATA_JSON = """
      {
        "x402Version": 2,
        "resource": {
          "url": "https://web3.okx.com/api/v6/dex/market/price-info",
          "mimeType": "application/json"
        },
        "accepts": [
          {
            "scheme": "exact",
            "network": "eip155:196",
            "amount": "500",
            "payTo": "0x0dedc3c5e15bee45166924ea5b02f54a35b1f9c6",
            "maxTimeoutSeconds": 86400,
            "asset": "0x4ae46a509f6b1d9056937ba4500cb143933d2dc8",
            "extra": {
              "version": "1",
              "transferMethod": "eip3009",
              "name": "Global Dollar",
              "symbol": "USDG"
            }
          },
          {
            "scheme": "exact",
            "network": "eip155:196",
            "amount": "500",
            "payTo": "0x0dedc3c5e15bee45166924ea5b02f54a35b1f9c6",
            "maxTimeoutSeconds": 86400,
            "asset": "0x779ded0c9e1022225f8e0630b35a9b54be713736",
            "extra": {
              "version": "1",
              "transferMethod": "eip3009",
              "name": "USD₮0",
              "symbol": "USD₮0"
            }
          }
        ]
      }
      """;

  private static X402Data parseX402Data(String json) {
    try {
      return new ObjectMapper().readValue(json, X402Data.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse X402_DATA_JSON", e);
    }
  }

  private static String parseChainId(String network) {
    // const match = network.match(/^eip155:(\d+)$/);
    Matcher matcher = Pattern.compile("^eip155:(\\d+)$").matcher(network);
    if (matcher.matches()) {
      return matcher.group(1);
    }
    return null;
  }

  @Test
  @DisplayName("测试EIP712签名")
  public void testEIP712Sign() throws Exception {
    String privateKey = "0x1234567890123456789012345678901234567890123456789012345678901234"; // 你的私钥 0x 开头

    X402Data X402_DATA = parseX402Data(X402_DATA_JSON);
    String payToken = "0x779ded0c9e1022225f8e0630b35a9b54be713736";
    X402_OKX_Custom.X402Accepted accepted = X402_DATA.accepts().stream().filter(a -> a.asset().equals(payToken))
        .findFirst().orElseThrow(() -> new RuntimeException("Accepted not found"));

    // EIP712 Domain
    Map<String, Object> domain = new LinkedHashMap<>();
    domain.put("name", accepted.extra().get("name"));
    domain.put("version", accepted.extra().get("version"));
    domain.put("chainId", parseChainId(accepted.network()));
    domain.put("verifyingContract", accepted.asset());

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
    message.put("to", accepted.payTo());
    message.put("value", accepted.amount());
    message.put("validAfter", "0");
    BigInteger validBefore = BigInteger.valueOf(System.currentTimeMillis() / 1000L)
        .add(BigInteger.valueOf(accepted.maxTimeoutSeconds()));
    message.put("validBefore", validBefore.toString());
    message.put("nonce", X402_OKX_Custom.generateNonce());

    String sig = X402_OKX_Custom.signTypedData(
        privateKey,
        domain,
        types,
        message,
        "TransferWithAuthorization");

    System.out.println("signature: " + sig); // 0x + r + s + v

    int x402Version = X402_DATA.x402Version();

    Object authorization = message; // message body

    X402_OKX_Custom.SignResult signResult = new X402_OKX_Custom.SignResult(sig, authorization);
    X402_OKX_Custom.X402Resource resource = X402_DATA.resource();
    String paymentSignature = X402_OKX_Custom.buildX402Header(x402Version, signResult, resource,
        accepted);
    System.out.println("paymentSignature: " + paymentSignature);
  }
}