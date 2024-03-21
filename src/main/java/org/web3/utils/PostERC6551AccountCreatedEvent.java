package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostERC6551AccountCreatedEvent {

    static Dotenv dotenv = Dotenv.load();
    // INFURA_HTTP_MAIN ALCHEMY_GOERLI_URL
    static String RPC_URL = dotenv.get("INFURA_HTTP_MAIN");
    static String Topic0 = "0x79f19b3655ee38b1ce526556b7731a20c8f218fbda4a3990b6cc4172fdf88722";

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        String blockNember = "19475678";

        String fromBlockHex = Numeric.toHexStringWithPrefix(new BigInteger(blockNember));
        String toBlockHex = Numeric.toHexStringWithPrefix(new BigInteger(blockNember));
        String requestBody_ = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"fromBlock\":\"%s\",\"toBlock\":\"%s\",\"topics\":[\"%s\"]}],\"id\":1}";
        String requestBody = String.format(requestBody_, fromBlockHex, toBlockHex, Topic0);

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url(RPC_URL)
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {

                JSONObject responseBody = new JSONObject(response.body().string());

                JSONArray result = responseBody.getJSONArray("result");

                if (result.length() > 0) {
                    // handle Response Result
                    handleResponseResult(result);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void handleResponseResult(JSONArray result) throws IOException {
        // event ERC6551AccountCreated(
        // address account,
        // address indexed implementation,
        // bytes32 salt,
        // uint256 chainId,
        // address indexed tokenContract,
        // uint256 indexed tokenId
        // );

        for (int i = 0; i < result.length(); i++) {

            if (result.getJSONObject(i).getString("address")
                    .equalsIgnoreCase("0x000000006551c19487814612e58fe06813775758")) {
                System.out.println("TokenBound v0.3.1");
            }
            JSONArray topics = result.getJSONObject(i).getJSONArray("topics");
            if (topics.length() == 4) {
                String topic0 = topics.getString(0);
                if (topic0.equalsIgnoreCase(Topic0)) {
                    String implementation = FunctionReturnDecoder.decodeAddress(topics.getString(1));

                    String tokenContract = FunctionReturnDecoder.decodeAddress(topics.getString(2));

                    BigInteger tokenId = Numeric.toBigInt(topics.getString(3));

                    System.out.println(implementation);
                    System.out.println(tokenContract);
                    System.out.println(tokenId);

                    String data = result.getJSONObject(i).getString("data");

                    System.out.println("================================");
                    List<TypeReference<Type>> outputParameters = new ArrayList<>();
                    outputParameters.add((TypeReference) new TypeReference<Address>() {
                    });
                    outputParameters.add((TypeReference) new TypeReference<Bytes32>() {
                    });
                    outputParameters.add((TypeReference) new TypeReference<Uint256>() {
                    });

                    List<Type> decodeDatas = FunctionReturnDecoder.decode(data, outputParameters);

                    String account = (String) decodeDatas.get(0).getValue();
                    String salt = Numeric.toHexString((byte[]) decodeDatas.get(1).getValue());
                    BigInteger chainId = (BigInteger) decodeDatas.get(2).getValue();

                    System.out.println("ERC6551 Account: " + account);
                    System.out.println(salt);
                    System.out.println(chainId);
                    System.out.println("================================");

                    String address = GetStorageAtSlot.GetDataStorageAt(RPC_URL, account,
                            "0x360894a13ba1a3210667c828492db98dca3e2076cc3735a920a3ca505d382bbc");
                    System.out.println("ERC6551 Account Impl: " + FunctionReturnDecoder.decodeAddress(address));

                }

            }
        }

    }

}
