package org.web3.okx;

import com.okx.x402.client.OKXHttpClient;
import com.okx.x402.crypto.OKXEvmSigner;
import com.okx.x402.crypto.OKXSignerFactory;
import com.okx.x402.crypto.OKXSignerFactory.OKXSignerConfig;
import com.okx.x402.util.OKXAuth;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Demo client showing automatic x402 payment flow.
 *
 * <p>
 * Flow: GET protected endpoint → auto-handles 402 → sign → retry → 200 + data
 * </p>
 */
public class X402DemoClient {

    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            // String privateKey = dotenv.get("PRIVATE_KEY");
            String privateKey = "0x1234567890123456789012345678901234567890123456789012345678901234";

            if (privateKey == null || privateKey.isEmpty()) {
                System.err.println("ERROR: PRIVATE_KEY environment variable is required.");
                System.exit(1);
            }

            String apiKey = dotenv.get("OKX_API_KEY");
            String secretKey = dotenv.get("OKX_SECRET_KEY");
            String passphrase = dotenv.get("OKX_PASSPHRASE");
            if (apiKey == null || secretKey == null || passphrase == null) {
                System.err.println(
                        "ERROR: OKX_API_KEY, OKX_SECRET_KEY, OKX_PASSPHRASE environment variables are required.");
                System.exit(1);
            }

            // Step 1: Create signer from private key
            OKXEvmSigner signer = OKXSignerFactory.createOKXSigner(
                    new OKXSignerConfig().privateKey(privateKey));
            System.out.println("Signer address: " + signer.getAddress());

            // Step 2: Create auto-402 handling client
            OKXHttpClient client = new OKXHttpClient(signer, "eip155:196");

            OKXAuth auth = new OKXAuth(apiKey, secretKey, passphrase);
            // const url = `${OKX_BASE_URL}${requestPath}`;
            String OKX_BASE_URL = "https://web3.okx.com";
            String OKX_CANDLES_PATH = "/api/v6/dex/market/candles";
            String queryParams = "chainIndex=1&tokenContractAddress=0x45804880de22913dafe09f4980848ece6ecbaf78&bar=1H&limit=300";
            String requestPath = OKX_CANDLES_PATH + "?" + queryParams;
            String uri = OKX_BASE_URL + requestPath;
            Map<String, String> headers = auth.createHeaders("GET", requestPath, "");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    // headers(name, value, name, value, ...) — must be an even number of strings
                    .headers(headers.entrySet().stream()
                            .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                            .toArray(String[]::new))
                    .build();
            HttpResponse<String> resp = client.request(request);

            System.out.println("Status: " + resp.statusCode());
            System.out.println("Body: " + resp.body());

            // Step 4: Print settlement proof
            String paymentResponse = resp.headers()
                    .firstValue("PAYMENT-RESPONSE").orElse(null);
            if (paymentResponse != null) {
                String json = new String(Base64.getDecoder().decode(paymentResponse));
                System.out.println("Settlement: " + json);
            }
        } catch (Exception e) {
            System.err.println("Payment failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}