package org.web3.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetTokenIdsOfAccountOnlyTest {

    static Dotenv dotenv = Dotenv.load();

    static String apiKey_E = dotenv.get("apiKey_goerli");
    static String apiKey_B = dotenv.get("apiKey_tbsc");

    public static void main(String[] args) {
        //
        String nftaddress = "0x0d3e02768ab63516ab5d386fad462214ca3e6a86";
        String account = "0x6278A1E803A76796a3A1f7F6344fE874ebfe94B2";

        String result_G = getTokenIdsOfContractAndAccount("5", nftaddress, account);
        System.out.println(result_G);

        System.out.println("`````````````````````");
        String result_TB = getTokenIdsOfContractAndAccount("97", "0xDb6c494BE6Aae80cc042f9CDA24Ce573aD163A46",
                account);
        System.out.println(result_TB);

        System.out.println("`````````````````````");
        String result_E = getTokenIdsOfContractAndAccount("1", "0x1b489201D974D37DDd2FaF6756106a7651914A63",
                "0xf77c3ea35684ed12617D4b27A0aB11605EA7de31");
        System.out.println(result_E);

        System.out.println("`````````````````````");
        String result_B = getTokenIdsOfContractAndAccount("56", "0xe88e04e739EB73978E76B6A20A86643f2A0E364a",
                "0x7Ae1Fda8e64e7e99D58D0f3EC7995455302eb5B9");
        System.out.println(result_B);
    }

    public static String getTokenIdsOfContractAndAccount(String chainId, String nftaddress, String account) {
        String urlLink = "";
        if (chainId == "1") {
            String url = "https://api.etherscan.io";
            String apiKey = apiKey_E;
            urlLink = url + "/api?module=account&action=tokennfttx&contractaddress=" + nftaddress +
                    "&address=" + account + "&startblock=0&endblock=latest&sort=asc&apikey=" + apiKey;
        } else if (chainId == "5") {
            String url = "https://api-goerli.etherscan.io";
            String apiKey = apiKey_E;
            urlLink = url + "/api?module=account&action=tokennfttx&contractaddress=" + nftaddress +
                    "&address=" + account + "&startblock=0&endblock=latest&sort=asc&apikey=" + apiKey;
        } else if (chainId == "97") {
            String url = "https://api-testnet.bscscan.com";
            String apiKey = apiKey_B;
            urlLink = url + "/api?module=account&action=tokennfttx&contractaddress=" + nftaddress +
                    "&address=" + account + "&startblock=0&endblock=latest&sort=asc&apikey=" + apiKey;
        } else if (chainId == "56") {
            String url = "https://api.bscscan.com";
            String apiKey = apiKey_B;
            urlLink = url + "/api?module=account&action=tokennfttx&contractaddress=" + nftaddress +
                    "&address=" + account + "&startblock=0&endblock=latest&sort=asc&apikey=" + apiKey;
        }

        // System.out.println(url);
        // System.out.println("Account: " + account);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlLink).build();
        try (Response response = client.newCall(request).execute()) {
            String responseData = response.body().string();
            JSONObject data = new JSONObject(responseData);

            if (data.getString("status").equals("1")) {
                Set<String> tokensSet = new HashSet<>();
                JSONArray result = data.getJSONArray("result");
                for (int i = 0; i < result.length(); i++) {
                    JSONObject item = result.getJSONObject(i);
                    if (item.getString("to").equalsIgnoreCase(account)) {
                        tokensSet.add(item.getString("tokenID"));
                    } else {
                        if (tokensSet.contains(item.getString("tokenID"))) {
                            tokensSet.remove(item.getString("tokenID"));
                        }
                    }
                }
                // System.out.println("该账户地址下持有该NFT的所有tokenIds:");
                List<String> ids = new ArrayList<>(tokensSet);
                // System.out.println("tokenIds: " + ids);
                List<JSONObject> resultList = new ArrayList<>();

                for (int i = 0; i < ids.size(); i++) {
                    JSONObject token = new JSONObject();
                    token.put("contract_address", nftaddress.toLowerCase());
                    token.put("token_id", ids.get(i));
                    token.put("owner", account.toLowerCase());

                    resultList.add(token);
                }

                JSONObject returndata = new JSONObject();
                returndata.put("code", 200);
                // returndata.put("message", "OK");

                JSONObject data_ = new JSONObject();
                data_.put("total", ids.size());
                data_.put("content", resultList);

                returndata.put("data", data_);
                return returndata.toString();
            } else {
                JSONObject nodata = new JSONObject();

                nodata.put("code", -200);
                JSONObject data_ = new JSONObject();
                nodata.put("data", data_);
                return nodata.toString();
            }
        } catch (Exception e) {
            System.err.println("查询 ERC721 Token 合约地址失败：" + e.getMessage());
            return null;
        }
    }
}
