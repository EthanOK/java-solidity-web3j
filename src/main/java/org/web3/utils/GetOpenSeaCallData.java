package org.web3.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.model.OpenseaInterface;
import org.web3j.model.OpenseaInterface.BasicOrderParameters;
import org.web3j.model.OpenseaInterface.ConsiderationItem;
import org.web3j.model.OpenseaInterface.AdditionalRecipient;
import org.web3j.model.OpenseaInterface.OrderParameters;
import org.web3j.model.OpenseaInterface.AdvancedOrder;
import org.web3j.model.OpenseaInterface.CriteriaResolver;
import org.web3j.model.OpenseaInterface.FulfillmentComponent;
import org.web3j.model.OpenseaInterface.OfferItem;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy.FirstCharBasedValidator;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetOpenSeaCallData {
        static Dotenv dotenv = Dotenv.load();
        static String OPENSEA_API_KEY = dotenv.get("OPENSEA_API_KEY");

        public static void main(String[] args) throws Exception {

                OpenseaInterface OpenSea = OpenseaInterface.load(null, null,
                                new ReadonlyTransactionManager(null, null), null);

                BigInteger weiValue = new BigInteger("0");
                // invalid 0xbc019d47824d6c0b2b5d4fd6563e5d2cf2d155b392b4453ac7130d1520fb78bb
                // 0x8fa8d6cee3583ece802e81e9ea4b54bd95bfab6482afe8f0a0f09d93ada9a5ae
                String hash_t = "0x8fa8d6cee3583ece802e81e9ea4b54bd95bfab6482afe8f0a0f09d93ada9a5ae";
                String chain_t = "goerli";
                String protocolAddress_t = "0x00000000000000adc04c56bf30ac9d3c0aaf14dc";
                String fulfiller_t = "0x6278a1e803a76796a3a1f7f6344fe874ebfe94b2";

                /*
                 * BasicOrderParameters parameters_test = getBasicOrderParameters(hash_t,
                 * chain_t, protocolAddress_t,
                 * fulfiller_t);
                 * 
                 * String calldata_fulfillBasicOrder_test =
                 * OpenSea.fulfillBasicOrder(parameters_test, weiValue)
                 * .encodeFunctionCall();
                 * System.out.println("calldata_fulfillBasicOrder_test:");
                 * System.out.println(calldata_fulfillBasicOrder_test);
                 */
                String[] hashs = { hash_t };

                // String hash_eth =
                // "0x7fa7680242ebcb783f17471c241c10a80899aa8678599d1221856f99e06b2082";
                // String chain_eth = "ethereum";
                // String protocolAddress_eth = "0x00000000000000ADc04C56Bf30aC9d3c0aAF14dC";
                // String fulfiller_eth = "0xc675897bb91797eaea7584f025a5533dbb13a000";
                // BasicOrderParameters parameters_eth = getBasicOrderParameters(hash_eth,
                // chain_eth, protocolAddress_eth,
                // fulfiller_eth);
                // String calldata_fulfillBasicOrder = OpenSea.fulfillBasicOrder(parameters_eth,
                // weiValue)
                // .encodeFunctionCall();
                // System.out.println("calldata_fulfillBasicOrder:");
                // System.out.println(calldata_fulfillBasicOrder);

                // TODO: fulfillAvailableAdvancedOrders(List<AdvancedOrder> advancedOrders,
                // List<CriteriaResolver> criteriaResolvers, List<FulfillmentComponent>
                // offerFulfillments, List<FulfillmentComponent> considerationFulfillments,
                // byte[] fulfillerConduitKey, String recipient, BigInteger maximumFulfilled,
                // BigInteger weiValue)

                // String fulfillmentData = getFulfillmentData(hash, chain, protocolAddress,
                // fulfiller);
                // System.out.println(fulfillmentData);

                // System.out.println(fulfillmentData_test);
                Map<String, Object> availableAdvancedOrdersDatas = getFulfillAvailableAdvancedOrdersParameters(hashs,
                                chain_t, protocolAddress_t,
                                fulfiller_t);
                List<AdvancedOrder> advancedOrders = (List<AdvancedOrder>) availableAdvancedOrdersDatas
                                .get("advancedOrders");
                List<CriteriaResolver> criteriaResolvers = (List<CriteriaResolver>) availableAdvancedOrdersDatas
                                .get("criteriaResolvers");
                List<FulfillmentComponent> offerFulfillments = (List<FulfillmentComponent>) availableAdvancedOrdersDatas
                                .get("offerFulfillments");
                List<FulfillmentComponent> considerationFulfillments = (List<FulfillmentComponent>) availableAdvancedOrdersDatas
                                .get("considerationFulfillments");
                byte[] fulfillerConduitKey = (byte[]) availableAdvancedOrdersDatas.get("fulfillerConduitKey");
                String recipient = (String) availableAdvancedOrdersDatas.get("recipient");
                BigInteger maximumFulfilled = (BigInteger) availableAdvancedOrdersDatas.get("maximumFulfilled");

                String calldata_fulfillAvailableAdvancedOrders = OpenSea
                                .fulfillAvailableAdvancedOrders(advancedOrders, criteriaResolvers,
                                                offerFulfillments,
                                                considerationFulfillments, fulfillerConduitKey, recipient,
                                                maximumFulfilled, weiValue)
                                .encodeFunctionCall();
                System.out.println("calldata_fulfillAvailableAdvancedOrders:");
                System.out.println(calldata_fulfillAvailableAdvancedOrders);

        }

        public static BasicOrderParameters getBasicOrderParameters_remove() {
                String considerationToken = "0x0000000000000000000000000000000000000000";

                BigInteger considerationIdentifier = new BigInteger("0");

                BigInteger considerationAmount = new BigInteger("48750000000000000");

                String offerer = "0x6278a1e803a76796a3a1f7f6344fe874ebfe94b2";

                String zone = "0x004c00500000ad104d7dbd00e3ae0a5c00560c00";

                String offerToken = "0xeaafcc17f28afe5cda5b3f76770efb7ef162d20b";

                BigInteger offerIdentifier = new BigInteger("19");

                BigInteger offerAmount = new BigInteger("1");

                BigInteger basicOrderType = new BigInteger("0");

                BigInteger startTime = new BigInteger("1685354843");

                BigInteger endTime = new BigInteger("1688033243");

                byte[] zoneHash = convertBytes("0x0000000000000000000000000000000000000000000000000000000000000000");

                BigInteger salt = new BigInteger(
                                "24446860302761739304752683030156737591518664810215442929815938533551652895526");

                byte[] offererConduitKey = convertBytes(
                                "0x0000007b02230091a7ed01230072f7006a004d60a8d4e71d599b8104250f0000");

                byte[] fulfillerConduitKey = convertBytes(
                                "0x0000007b02230091a7ed01230072f7006a004d60a8d4e71d599b8104250f0000");

                BigInteger totalOriginalAdditionalRecipients = new BigInteger("1");

                List<AdditionalRecipient> additionalRecipients = new ArrayList<>();
                BigInteger amount = new BigInteger("1250000000000000");
                String recipient = "0x0000a26b00c1f0df003000390027140000faa719";
                AdditionalRecipient additionalRecipient0 = new AdditionalRecipient(amount, recipient);
                additionalRecipients.add(additionalRecipient0);
                byte[] signature = convertBytes(
                                "0x38e79db0c766467da4c5a5b2607403aa286aefa37d1243e7576f570e3264aa62a793c532436a923a0ebd357bdb83076f41a689335da2d0b0419e8afb8abf85f0");

                BasicOrderParameters basicOrderParameters = new BasicOrderParameters(considerationToken,
                                considerationIdentifier, considerationAmount, offerer, zone, offerToken,
                                offerIdentifier, offerAmount, basicOrderType,
                                startTime, endTime, zoneHash, salt, offererConduitKey, fulfillerConduitKey,
                                totalOriginalAdditionalRecipients, additionalRecipients,
                                signature);

                return basicOrderParameters;

        }

        public static byte[] convertBytes(String dataHex) {
                byte[] result = Numeric.hexStringToByteArray(dataHex);
                return result;
        }

        public static Map<String, Object> getFulfillAvailableAdvancedOrdersParameters(String[] hashs, String chainName,
                        String protocolAddress, String fulfiller)
                        throws Exception {
                Map<String, Object> availableAdvancedOrdersDatas = new HashMap<>();
                List<AdvancedOrder> advancedOrders = new ArrayList<>();
                List<CriteriaResolver> criteriaResolvers = new ArrayList<>();
                List<FulfillmentComponent> offerFulfillments = new ArrayList<>();
                List<FulfillmentComponent> considerationFulfillments = new ArrayList<>();
                byte[] fulfillerConduitKey = convertBytes(
                                "0x0000000000000000000000000000000000000000000000000000000000000000");
                String recipient = fulfiller;
                BigInteger maximumFulfilled = BigInteger.valueOf(hashs.length);

                for (int i = 0; i < hashs.length; i++) {

                        Map<String, Object> resultDatas = getOrderParametersAndSignature(hashs[i],
                                        chainName, protocolAddress,
                                        fulfiller);
                        OrderParameters orderParameters = (OrderParameters) resultDatas.get("orderParameters");
                        String signatureHex = (String) resultDatas.get("signature");
                        BigInteger numerator = new BigInteger("1");

                        BigInteger denominator = new BigInteger("1");

                        byte[] signature = convertBytes(signatureHex);

                        byte[] extraData = convertBytes("0x");

                        AdvancedOrder advancedOrder = new AdvancedOrder(orderParameters, numerator, denominator,
                                        signature,
                                        extraData);
                        advancedOrders.add(advancedOrder);

                        for (int j = 0; j < orderParameters.offer.size(); j++) {
                                BigInteger orderIndex = BigInteger.valueOf(i);

                                BigInteger itemIndex = BigInteger.valueOf(j);
                                FulfillmentComponent fulfillmentComponent = new FulfillmentComponent(orderIndex,
                                                itemIndex);
                                // TODO ???
                                // List<FulfillmentComponent> temp = new ArrayList<>();
                                // temp.add(fulfillmentComponent);
                                offerFulfillments.add(fulfillmentComponent);
                        }
                        for (int j = 0; j < orderParameters.consideration.size(); j++) {
                                BigInteger orderIndex = BigInteger.valueOf(i);

                                BigInteger itemIndex = BigInteger.valueOf(j);
                                FulfillmentComponent fulfillmentComponent = new FulfillmentComponent(orderIndex,
                                                itemIndex);
                                // TODO ???
                                // List<FulfillmentComponent> temp = new ArrayList<>();
                                // temp.add(fulfillmentComponent);
                                considerationFulfillments.add(fulfillmentComponent);
                        }

                }
                availableAdvancedOrdersDatas.put("advancedOrders", advancedOrders);
                availableAdvancedOrdersDatas.put("criteriaResolvers", criteriaResolvers);
                availableAdvancedOrdersDatas.put("offerFulfillments", offerFulfillments);
                availableAdvancedOrdersDatas.put("considerationFulfillments", considerationFulfillments);
                availableAdvancedOrdersDatas.put("fulfillerConduitKey", fulfillerConduitKey);
                availableAdvancedOrdersDatas.put("recipient", recipient);
                availableAdvancedOrdersDatas.put("maximumFulfilled", maximumFulfilled);
                return availableAdvancedOrdersDatas;

        }

        public static BasicOrderParameters getBasicOrderParameters(String hash, String chainName,
                        String protocolAddress,
                        String fulfiller)
                        throws Exception {
                String responseString = null;
                BasicOrderParameters basicOrderParameters = null;

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");

                String requestBodyString = "{\"listing\":{\"hash\":\"" + hash +
                                "\",\"chain\":\"" + chainName
                                + "\",\"protocol_address\":\"" + protocolAddress +
                                "\"},\"fulfiller\":{\"address\":\""
                                + fulfiller + "\"}}";

                RequestBody requestBody = RequestBody.Companion.create(requestBodyString, mediaType);
                chainName = chainName.toLowerCase();
                Request request = null;
                if (chainName.equals("goerli")) {
                        request = new Request.Builder()
                                        .url("https://testnets-api.opensea.io/v2/listings/fulfillment_data")
                                        .post(requestBody)
                                        .addHeader("content-type", "application/json")
                                        .build();

                        // test network need wait 1s
                        Thread.sleep(1000);
                        System.out.println(new Date());

                }
                if (chainName.equals("ethereum")) {

                        request = new Request.Builder()
                                        .url("https://api.opensea.io/v2/listings/fulfillment_data")
                                        .post(requestBody)
                                        .addHeader("content-type", "application/json")
                                        .addHeader("X-API-KEY", OPENSEA_API_KEY)
                                        .build();
                }

                try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful()) {
                                // Handle successful response

                                ResponseBody responseBody = response.body();
                                if (responseBody != null) {
                                        responseString = responseBody.string();
                                        // System.out.println(responseString);
                                        // 将 JSON 响应转换为 JSONObject 对象
                                        JSONObject json = new JSONObject(responseString);

                                        String protocol = json.getString("protocol");
                                        System.out.println("protocol:" + protocol);
                                        JSONObject fulfillment_data = json.getJSONObject("fulfillment_data");
                                        // System.out.println(fulfillment_data);
                                        JSONObject transaction = fulfillment_data.getJSONObject("transaction");
                                        BigInteger chain = transaction.getBigInteger("chain");
                                        System.out.println("chain:" + chain);

                                        String to = transaction.getString("to");
                                        System.out.println("to:" + to);
                                        BigInteger value = transaction.getBigInteger("value");
                                        System.out.println("value:" + value);
                                        JSONObject input_data = transaction.getJSONObject("input_data");

                                        JSONObject parameters = input_data.getJSONObject("parameters");

                                        // System.out.println(parameters);

                                        String considerationToken = parameters.getString("considerationToken");

                                        BigInteger considerationIdentifier = new BigInteger(
                                                        parameters.getString("considerationIdentifier"));

                                        BigInteger considerationAmount = new BigInteger(
                                                        parameters.getString("considerationAmount"));

                                        String offerer = parameters.getString("offerer");

                                        String zone = parameters.getString("zone");

                                        String offerToken = parameters.getString("offerToken");

                                        BigInteger offerIdentifier = new BigInteger(
                                                        parameters.getString("offerIdentifier"));

                                        BigInteger offerAmount = new BigInteger(parameters.getString("offerAmount"));

                                        BigInteger basicOrderType = parameters.getBigInteger("basicOrderType");

                                        BigInteger startTime = new BigInteger(parameters.getString("startTime"));

                                        BigInteger endTime = new BigInteger(parameters.getString("endTime"));

                                        byte[] zoneHash = convertBytes(
                                                        parameters.getString("zoneHash"));

                                        BigInteger salt = new BigInteger(
                                                        parameters.getString("salt"));

                                        byte[] offererConduitKey = convertBytes(
                                                        parameters.getString("offererConduitKey"));

                                        byte[] fulfillerConduitKey = convertBytes(
                                                        parameters.getString("fulfillerConduitKey"));

                                        BigInteger totalOriginalAdditionalRecipients = new BigInteger(
                                                        parameters.getString("totalOriginalAdditionalRecipients"));

                                        List<AdditionalRecipient> additionalRecipients = new ArrayList<>();

                                        JSONArray additionalRecipientsArray = parameters
                                                        .getJSONArray("additionalRecipients");

                                        for (int i = 0; i < additionalRecipientsArray.length(); i++) {
                                                JSONObject additionalRecipientObject = additionalRecipientsArray
                                                                .getJSONObject(i);

                                                BigInteger amount = new BigInteger(
                                                                additionalRecipientObject.getString("amount"));
                                                String recipient = additionalRecipientObject.getString("recipient");
                                                AdditionalRecipient additionalRecipient = new AdditionalRecipient(
                                                                amount,
                                                                recipient);
                                                additionalRecipients.add(additionalRecipient);
                                        }

                                        byte[] signature = convertBytes(parameters.getString("signature"));
                                        basicOrderParameters = new BasicOrderParameters(
                                                        considerationToken,
                                                        considerationIdentifier, considerationAmount, offerer, zone,
                                                        offerToken,
                                                        offerIdentifier, offerAmount, basicOrderType,
                                                        startTime, endTime, zoneHash, salt, offererConduitKey,
                                                        fulfillerConduitKey,
                                                        totalOriginalAdditionalRecipients, additionalRecipients,
                                                        signature);

                                } else {
                                        // Handle unsuccessful response
                                        throw new Exception(
                                                        "Failed to get fulfillment data");
                                }

                        } else {
                                // Handle unsuccessful response
                                throw new Exception(
                                                "Failed to get fulfillment data");
                        }
                } catch (Exception e) {
                        // Handle exception
                        System.out.println("Order invalid");
                        throw new Exception(e);
                }

                return basicOrderParameters;
        }

        public static Map<String, Object> getOrderParametersAndSignature(String hash, String chainName,
                        String protocolAddress,
                        String fulfiller)
                        throws Exception {

                Map<String, Object> resultDatas = new HashMap<>();

                String responseString = null;
                OrderParameters orderParameters = null;

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");

                String requestBodyString = "{\"listing\":{\"hash\":\"" + hash +
                                "\",\"chain\":\"" + chainName
                                + "\",\"protocol_address\":\"" + protocolAddress +
                                "\"},\"fulfiller\":{\"address\":\""
                                + fulfiller + "\"}}";

                RequestBody requestBody = RequestBody.Companion.create(requestBodyString, mediaType);
                chainName = chainName.toLowerCase();
                Request request = null;
                if (chainName.equals("goerli")) {
                        request = new Request.Builder()
                                        .url("https://testnets-api.opensea.io/v2/listings/fulfillment_data")
                                        .post(requestBody)
                                        .addHeader("content-type", "application/json")
                                        .build();

                        // test network need wait 1s
                        Thread.sleep(1000);
                        System.out.println(new Date());

                }
                if (chainName.equals("ethereum")) {

                        request = new Request.Builder()
                                        .url("https://api.opensea.io/v2/listings/fulfillment_data")
                                        .post(requestBody)
                                        .addHeader("content-type", "application/json")
                                        .addHeader("X-API-KEY", OPENSEA_API_KEY)
                                        .build();
                }

                try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful()) {
                                // Handle successful response

                                ResponseBody responseBody = response.body();
                                if (responseBody != null) {
                                        responseString = responseBody.string();
                                        // System.out.println(responseString);
                                        // 将 JSON 响应转换为 JSONObject 对象
                                        JSONObject json = new JSONObject(responseString);

                                        String protocol = json.getString("protocol");
                                        System.out.println("protocol:" + protocol);
                                        JSONObject fulfillment_data = json.getJSONObject("fulfillment_data");
                                        // System.out.println(fulfillment_data);
                                        // orders
                                        JSONArray orders = fulfillment_data
                                                        .getJSONArray("orders");
                                        if (orders.length() == 0) {
                                                // Handle unsuccessful response
                                                throw new Exception(
                                                                "Failed to get fulfillment data");
                                        }
                                        JSONObject order = orders.getJSONObject(0);

                                        String signature = order.getString("signature");
                                        JSONObject parameters = order.getJSONObject("parameters");

                                        // data
                                        String offerer = parameters.getString("offerer");
                                        String zone = parameters.getString("zone");

                                        List<OfferItem> offer = new ArrayList<>();
                                        JSONArray offerArray = parameters.getJSONArray("offer");
                                        for (int i = 0; i < offerArray.length(); i++) {
                                                JSONObject offerObject = offerArray.getJSONObject(i);
                                                BigInteger itemType = offerObject.getBigInteger("itemType");

                                                String token = offerObject.getString("token");

                                                BigInteger identifierOrCriteria = offerObject
                                                                .getBigInteger("identifierOrCriteria");

                                                BigInteger startAmount = offerObject.getBigInteger("startAmount");

                                                BigInteger endAmount = offerObject.getBigInteger("endAmount");
                                                OfferItem offerItem = new OfferItem(itemType, token,
                                                                identifierOrCriteria, startAmount, endAmount);

                                                offer.add(offerItem);

                                        }
                                        List<ConsiderationItem> consideration = new ArrayList<>();
                                        ;
                                        JSONArray considerationArray = parameters.getJSONArray("consideration");
                                        for (int i = 0; i < considerationArray.length(); i++) {
                                                JSONObject considerationObject = considerationArray.getJSONObject(i);
                                                BigInteger itemType = considerationObject.getBigInteger("itemType");

                                                String token = considerationObject.getString("token");

                                                BigInteger identifierOrCriteria = considerationObject
                                                                .getBigInteger("identifierOrCriteria");

                                                BigInteger startAmount = considerationObject
                                                                .getBigInteger("startAmount");

                                                BigInteger endAmount = considerationObject.getBigInteger("endAmount");
                                                String recipient = considerationObject.getString("recipient");
                                                ConsiderationItem considerationItem = new ConsiderationItem(itemType,
                                                                token, identifierOrCriteria, startAmount, endAmount,
                                                                recipient);
                                                consideration.add(considerationItem);

                                        }

                                        BigInteger orderType = parameters.getBigInteger("orderType");

                                        BigInteger startTime = parameters.getBigInteger("startTime");

                                        BigInteger endTime = parameters.getBigInteger("endTime");

                                        byte[] zoneHash = convertBytes(parameters.getString("zoneHash"));
                                        String saltString = parameters.getString("salt");

                                        BigInteger salt = new BigInteger("0");
                                        if (saltString.matches("^0x[0-9A-Fa-f]+$")) {
                                                salt = Numeric.toBigInt(saltString);

                                        } else {
                                                salt = new BigInteger(saltString);
                                        }

                                        byte[] conduitKey = convertBytes(parameters.getString("conduitKey"));

                                        BigInteger totalOriginalConsiderationItems = parameters
                                                        .getBigInteger("totalOriginalConsiderationItems");

                                        orderParameters = new OrderParameters(offerer, zone, offer, consideration,
                                                        orderType,
                                                        startTime,
                                                        endTime,
                                                        zoneHash, salt, conduitKey,
                                                        totalOriginalConsiderationItems);

                                        resultDatas.put("orderParameters", orderParameters);
                                        resultDatas.put("signature", signature);

                                } else {
                                        // Handle unsuccessful response
                                        throw new Exception(
                                                        "Failed to get fulfillment data");
                                }

                        } else {
                                // Handle unsuccessful response
                                throw new Exception(
                                                "Failed to get fulfillment data");
                        }
                } catch (Exception e) {
                        // Handle exception

                        throw new Exception(e);
                }

                return resultDatas;
        }

}
