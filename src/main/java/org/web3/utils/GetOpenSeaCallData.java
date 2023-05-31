package org.web3.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.web3j.model.OpenseaInterface;
import org.web3j.model.OpenseaInterface.BasicOrderParameters;
import org.web3j.model.OpenseaInterface.AdditionalRecipient;
import org.web3j.model.OpenseaInterface.AdvancedOrder;
import org.web3j.model.OpenseaInterface.CriteriaResolver;
import org.web3j.model.OpenseaInterface.FulfillmentComponent;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.utils.Numeric;

public class GetOpenSeaCallData {

        public static void main(String[] args) throws Exception {

                OpenseaInterface OpenSea = OpenseaInterface.load(null, null,
                                new ReadonlyTransactionManager(null, null), null);

                BigInteger weiValue = new BigInteger("0");
                BasicOrderParameters parameters = getBasicOrderParameters();

                // TODO: fulfillBasicOrder(BasicOrderParameters parameters, BigInteger weiValue)
                String calldata_fulfillBasicOrder = OpenSea.fulfillBasicOrder(parameters, weiValue)
                                .encodeFunctionCall();
                System.out.println("calldata_fulfillBasicOrder:");
                System.out.println(calldata_fulfillBasicOrder);

                // TODO: fulfillAvailableAdvancedOrders(List<AdvancedOrder> advancedOrders,
                // List<CriteriaResolver> criteriaResolvers, List<FulfillmentComponent>
                // offerFulfillments, List<FulfillmentComponent> considerationFulfillments,
                // byte[] fulfillerConduitKey, String recipient, BigInteger maximumFulfilled,
                // BigInteger weiValue)
                List<AdvancedOrder> advancedOrders = new ArrayList<>();
                List<CriteriaResolver> criteriaResolvers = new ArrayList<>();
                List<FulfillmentComponent> offerFulfillments = new ArrayList<>();
                List<FulfillmentComponent> considerationFulfillments = new ArrayList<>();
                byte[] fulfillerConduitKey = convertBytes("0x");
                String recipient = "";
                BigInteger maximumFulfilled = new BigInteger("0");
                ;
                String calldata_fulfillAvailableAdvancedOrders = OpenSea
                                .fulfillAvailableAdvancedOrders(advancedOrders, criteriaResolvers, offerFulfillments,
                                                considerationFulfillments, fulfillerConduitKey, recipient,
                                                maximumFulfilled, weiValue)
                                .encodeFunctionCall();
                System.out.println("calldata_fulfillAvailableAdvancedOrders:");
                System.out.println(calldata_fulfillAvailableAdvancedOrders);

        }

        public static BasicOrderParameters getBasicOrderParameters() {
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
}
