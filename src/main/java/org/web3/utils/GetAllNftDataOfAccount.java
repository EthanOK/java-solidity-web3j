package org.web3.utils;

import java.util.*;
import org.json.*;
import okhttp3.*;

public class GetAllNftDataOfAccount {
    public static void main(String[] args) {
        //
        String account = "0x6278A1E803A76796a3A1f7F6344fE874ebfe94B2";
        String result = getAllNftAddressOfAccount(account);
        System.out.println(result);
    }

    public static String getAllNftAddressOfAccount(String account) {
        String url = "https://api-goerli.etherscan.io";
        String apiKey = "AH5D81QUX5NHUQ9FBIKWZRC9YK6Y7S71XM";
        url = url + "/api?module=account&action=tokennfttx&address=" + account
                + "&startblock=0&endblock=latest&sort=asc&apikey=" + apiKey;
        // System.out.println(url);
        // System.out.println("Account: " + account);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String responseData = response.body().string();
            JSONObject data = new JSONObject(responseData);
            if (data.getString("status").equals("1")) {
                Map<String, Integer> contractCounts = new HashMap<>();
                Map<String, String> contractNames = new HashMap<>();
                Map<String, String> contractSymbol = new HashMap<>();
                JSONArray results = data.getJSONArray("result");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject item = results.getJSONObject(i);
                    String contractAddress = item.getString("contractAddress");
                    if (contractCounts.containsKey(contractAddress)) {
                        int counts;
                        if (item.getString("to").equalsIgnoreCase(account)) {
                            counts = contractCounts.get(contractAddress) + 1;
                        } else {
                            counts = contractCounts.get(contractAddress) - 1;
                        }
                        contractCounts.put(contractAddress, counts);
                    } else {
                        contractCounts.put(contractAddress, 1);
                        contractNames.put(contractAddress, item.getString("tokenName"));
                        contractSymbol.put(contractAddress, item.getString("tokenSymbol"));
                    }
                }
                // System.out.println("该账户地址下 ERC721 Token 合约地址及持有数量：");
                List<JSONObject> result = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : contractCounts.entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    if (value > 0) {
                        JSONObject item = new JSONObject();
                        item.put("TokenAddress", key);
                        item.put("TokenName", contractNames.get(key));
                        item.put("TokenSymbol", contractSymbol.get(key));
                        item.put("TokenQuantity", Integer.toString(value));
                        result.add(item);
                    }
                }
                JSONObject returndata = new JSONObject();
                returndata.put("status", "1");
                returndata.put("message", "OK");
                returndata.put("result", new JSONArray(result));
                String jsonString = returndata.toString();
                // System.out.println(jsonString);
                return jsonString;
            } else {
                JSONObject nodata = new JSONObject();
                nodata.put("status", "0");
                nodata.put("message", "No transactions found");
                nodata.put("result", new JSONArray());
                return nodata.toString();
            }
        } catch (Exception e) {
            System.err.println("查询 ERC721 Token 合约地址失败：" + e.getMessage());
            return null;
        }
    }
}
