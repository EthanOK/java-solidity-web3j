package org.web3.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.utils.Numeric;
import org.web3j.ens.NameHash;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetENSOfAddress {

    static Dotenv dotenv = Dotenv.load();
    static String GET_ENS_URL = dotenv.get("GET_ENS_URL");

    public static void main(String[] args) throws IOException {
        String address = "0xda482ddf91922e4ae66fa1aa82290e9b70a4693b";
        String ens = getENSOfAddress(address);
        System.out.println(ens);
    }

    public static String getENSOfAddress(String address) throws IOException {
        String reverseName = Numeric.cleanHexPrefix(address) + ".addr.reverse";
        String reverseNameHex = NameHash.dnsEncode(reverseName);
        String dataParams = encodeFunctionCall(reverseNameHex);

        String postData = "{\"method\":\"eth_call\",\"params\":[{\"to\":\"0xc0497e381f536be9ce14b0dd3817cbcae57d2f62\","
                +
                "\"data\":\"" + dataParams + "\"},"
                + "\"latest\"],\"id\":44,\"jsonrpc\":\"2.0\"}";

        try {
            String response = sendPostRequest(GET_ENS_URL, postData);
            JSONObject json = new JSONObject(response);

            if (json.has("result")) {
                String result = json.getString("result");

                List<Type> listResult = decodeFunctionCall(result);
                if (!listResult.isEmpty()) {
                    // 获取第一个元素，即第一个返回值
                    Type firstValue = listResult.get(0);
                    return (String) firstValue.getValue();
                }

                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendPostRequest(String url, String postData) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(postData, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            return response.body().string();

        }

    }

    public static List<Type> decodeFunctionCall(String decodedData) {

        Function function = new Function("reverse",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Utf8String>() {
                },
                        new TypeReference<Address>() {
                        },
                        new TypeReference<Address>() {
                        },
                        new TypeReference<Address>() {
                        }));

        return FunctionReturnDecoder.decode(decodedData, function.getOutputParameters());
    }

    public static String encodeFunctionCall(String reverseNameHex) {

        Function function = new Function("reverse",
                Arrays.asList(new DynamicBytes(Numeric.hexStringToByteArray(reverseNameHex))),
                Arrays.asList(new TypeReference<Utf8String>() {
                },
                        new TypeReference<Address>() {
                        },
                        new TypeReference<Address>() {
                        },
                        new TypeReference<Address>() {
                        }));

        String encodedHex = FunctionEncoder.encode(function);
        return encodedHex;
    }
}
