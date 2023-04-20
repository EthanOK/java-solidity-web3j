package org.web3.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NFTsForCollection {
    public static void main(String[] args) throws IOException, InterruptedException {
        // https://eth-goerli.g.alchemy.com 83yL2qXH68vnTkzzida15zCVNGZCy1JO
        // https://eth-mainnet.g.alchemy.com 7jrSQ_ID86i4kJgYy9xk_lFZAecEZNDv
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://eth-goerli.g.alchemy.com/nft/v2/83yL2qXH68vnTkzzida15zCVNGZCy1JO/getNFTsForCollection?contractAddress=0x09e8617f391c54530CC2D3762ceb1dA9F840c5a3&withMetadata=true&startToken=100"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // https://eth-goerli.g.alchemy.com/nft/v2/83yL2qXH68vnTkzzida15zCVNGZCy1JO/getNFTsForCollection?contractAddress=0x28D1bC817DE02C9f105A6986eF85cB04863C3042&withMetadata=true"
    }
}
