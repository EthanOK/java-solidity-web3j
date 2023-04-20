package org.web3.utils;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NftClientOk {
    // 一次调用最多只能查100条数据 需要while循环查更多的数据
    private static final String URI_TEMPLATE = "%s/nft/v2/%s/getNFTsForCollection?contractAddress=%s&withMetadata=true&startToken=%s";

    public static void main(String[] args) throws Exception {
        List<String> s = getAllNfts("0x97f236E644db7Be9B8308525e6506E4B3304dA7B");
        System.out.println(s);
    }

    public static List<String> getAllNfts(String contractAddress) throws Exception {
        List<String> allNftsTokenIds = new ArrayList<>();
        String mainnet = "https://eth-mainnet.g.alchemy.com";
        String apiKey_mainnet = "";
        String goerli = "https://eth-goerli.g.alchemy.com";
        String apiKey_goerli = "";
        String nextToken = "";
        while (nextToken != null) {
            String uri = String.format(URI_TEMPLATE, goerli, apiKey_goerli, contractAddress,
                    URLEncoder.encode(nextToken, StandardCharsets.UTF_8));
            System.out.println(uri);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(uri)
                    .addHeader("accept", "application/json")
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            int statusCode = response.code();
            if (statusCode != 200) {
                throw new Exception(
                        String.format("Failed to get NFTs, status code: %d, body: %s", statusCode, responseBody));
            }

            // 将 JSON 响应转换为 JSONObject 对象
            JSONObject json = new JSONObject(responseBody);

            // 从 JSONObject 中获取 "nfts" 数组
            JSONArray nfts = json.getJSONArray("nfts");

            // 遍历 "nfts" 数组
            for (int i = 0; i < nfts.length(); i++) {
                JSONObject nft = nfts.getJSONObject(i);
                JSONObject id = nft.getJSONObject("id");
                String tokenId = id.getString("tokenId");

                // 在这里处理 tokenId 数据
                BigInteger bigInteger = new BigInteger(tokenId.substring(2), 16);
                String decimalString = bigInteger.toString();
                System.out.println("tokenId:" + decimalString);
                allNftsTokenIds.add(decimalString);

                // tokenUri
                String tokenUri = nft.getJSONObject("tokenUri").getString("raw");
                System.out.println("tokenUri:" + tokenUri);

                // metadata
                JSONObject metadata = nft.getJSONObject("metadata");
                System.out.println("metadata:" + metadata);
            }

            // nextToken is null?
            nextToken = json.optString("nextToken", null);
        }
        return allNftsTokenIds;
    }

}
