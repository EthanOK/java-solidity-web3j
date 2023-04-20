package org.web3.utils;

import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class NftClient {
    // 一次调用最多只能查100条数据 需要while循环查更多的数据
    private static final String URI_TEMPLATE = "%s/nft/v2/%s/getNFTsForCollection?contractAddress=%s&withMetadata=true&startToken=%s";
    static Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) throws Exception {
        String mainnetAlchemy = "https://eth-mainnet.g.alchemy.com";
        String goerliAlchemy = "https://eth-goerli.g.alchemy.com";
        String apiKey_goerli = dotenv.get("apiKey_goerli");// 0x28D1bC817DE02C9f105A6986eF85cB04863C3042
        List<String> s = getAllNfts("0x28D1bC817DE02C9f105A6986eF85cB04863C3042", goerliAlchemy, apiKey_goerli);
        System.out.println(s);
    }

    public static List<String> getAllNfts(String contractAddress, String blockNet, String apiKeyAlchemy)
            throws Exception {
        List<String> allNftsTokenIds = new ArrayList<>();

        String nextToken = "";
        while (nextToken != null) {
            String uri = String.format(URI_TEMPLATE, blockNet, apiKeyAlchemy, contractAddress,
                    URLEncoder.encode(nextToken, StandardCharsets.UTF_8));
            System.out.println(uri);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new Exception(
                        String.format("Failed to get NFTs, status code: %d, body: %s", statusCode, responseBody));
            }

            // 将 JSON 响应转换为 JSONObject 对象
            JSONObject json = new JSONObject(responseBody);

            // 从 JSONObject 中获取 "nfts" 数组
            JSONArray nfts = json.getJSONArray("nfts");

            // 遍历 "nfts" 数组
            // contractMetadata":{"name":"YGME","symbol":"YGME"}
            JSONObject contractMetadata = nfts.getJSONObject(0).getJSONObject("contractMetadata");
            String name = contractMetadata.getString("name");
            String symbol = contractMetadata.getString("symbol");
            System.out.println("name:" + name);
            System.out.println("symbol:" + symbol);
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

            // nextToken is key ?
            nextToken = json.optString("nextToken", null);
        }
        return allNftsTokenIds;
    }

}
