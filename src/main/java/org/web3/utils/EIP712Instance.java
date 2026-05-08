package org.web3.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.util.*;

public class EIP712Instance {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String signTypedData(
            String privateKey,
            Map<String, Object> domain,
            Map<String, List<Map<String, String>>> types,
            Map<String, Object> message,
            String primaryType) throws Exception {

        // ⚠️ ethers 不需要你传 EIP712Domain，这里要自动补
        types = new LinkedHashMap<>(types);
        types.put("EIP712Domain", buildDomainTypes(domain));

        // 构造完整 JSON
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("types", types);
        data.put("primaryType", primaryType);
        data.put("domain", domain);
        data.put("message", normalizeMessage(message));

        String json = mapper.writeValueAsString(data);

        // EIP712 编码
        StructuredDataEncoder encoder = new StructuredDataEncoder(json);
        byte[] hash = encoder.hashStructuredData();

        // 签名
        ECKeyPair keyPair = ECKeyPair.create(Numeric.hexStringToByteArray(privateKey));
        Sign.SignatureData sig = Sign.signMessage(hash, keyPair, false);

        String signature = Numeric.toHexString(sig.getR())
                + Numeric.toHexStringNoPrefix(sig.getS())
                + Numeric.toHexStringNoPrefix(sig.getV());

        return signature;
    }

    // ------------------------
    // 自动构造 Domain Types
    // ------------------------
    private static List<Map<String, String>> buildDomainTypes(Map<String, Object> domain) {
        List<Map<String, String>> list = new ArrayList<>();

        if (domain.containsKey("name"))
            list.add(type("name", "string"));
        if (domain.containsKey("version"))
            list.add(type("version", "string"));
        if (domain.containsKey("chainId"))
            list.add(type("chainId", "uint256"));
        if (domain.containsKey("verifyingContract"))
            list.add(type("verifyingContract", "address"));
        if (domain.containsKey("salt"))
            list.add(type("salt", "bytes32"));

        return list;
    }

    private static Map<String, String> type(String name, String type) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("name", name);
        m.put("type", type);
        return m;
    }

    // ------------------------
    // 规范化 message（关键）
    // ------------------------
    private static Map<String, Object> normalizeMessage(Map<String, Object> message) {
        Map<String, Object> out = new LinkedHashMap<>();

        for (Map.Entry<String, Object> e : message.entrySet()) {
            Object v = e.getValue();

            if (v instanceof Number) {
                // 👉 uint256 一律转 string（对齐 ethers）
                out.put(e.getKey(), v.toString());
            } else {
                out.put(e.getKey(), v);
            }
        }
        return out;
    }

}