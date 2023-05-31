package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint120;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.abi.datatypes.reflection.Parameterized;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class OpenseaInterface extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_FULFILLAVAILABLEADVANCEDORDERS = "fulfillAvailableAdvancedOrders";

    public static final String FUNC_FULFILLBASICORDER = "fulfillBasicOrder";

    public static final String FUNC_FULFILLBASICORDER_EFFICIENT_6GL6YC = "fulfillBasicOrder_efficient_6GL6yc";

    public static final String FUNC_GETORDERSTATUS = "getOrderStatus";

    public static final String FUNC_INFORMATION = "information";

    public static final String FUNC_NAME = "name";

    @Deprecated
    protected OpenseaInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OpenseaInterface(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OpenseaInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OpenseaInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> fulfillAvailableAdvancedOrders(List<AdvancedOrder> advancedOrders, List<CriteriaResolver> criteriaResolvers, List<FulfillmentComponent> offerFulfillments, List<FulfillmentComponent> considerationFulfillments, byte[] fulfillerConduitKey, String recipient, BigInteger maximumFulfilled, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_FULFILLAVAILABLEADVANCEDORDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<AdvancedOrder>(AdvancedOrder.class, advancedOrders), 
                new org.web3j.abi.datatypes.DynamicArray<CriteriaResolver>(CriteriaResolver.class, criteriaResolvers), 
                new org.web3j.abi.datatypes.DynamicArray<FulfillmentComponent>(
                        FulfillmentComponent.class,
                        org.web3j.abi.Utils.typeMap(offerFulfillments, FulfillmentComponent.class)), 
                new org.web3j.abi.datatypes.DynamicArray<FulfillmentComponent>(
                        FulfillmentComponent.class,
                        org.web3j.abi.Utils.typeMap(considerationFulfillments, FulfillmentComponent.class)), 
                new org.web3j.abi.datatypes.generated.Bytes32(fulfillerConduitKey), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(maximumFulfilled)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> fulfillBasicOrder(BasicOrderParameters parameters, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_FULFILLBASICORDER, 
                Arrays.<Type>asList(parameters), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> fulfillBasicOrder_efficient_6GL6yc(BasicOrderParameters parameters, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_FULFILLBASICORDER_EFFICIENT_6GL6YC, 
                Arrays.<Type>asList(parameters), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple4<Boolean, Boolean, BigInteger, BigInteger>> getOrderStatus(byte[] orderHash) {
        final Function function = new Function(FUNC_GETORDERSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(orderHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<Boolean, Boolean, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<Boolean, Boolean, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<Boolean, Boolean, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Boolean, Boolean, BigInteger, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<String, byte[], String>> information() {
        final Function function = new Function(FUNC_INFORMATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple3<String, byte[], String>>(function,
                new Callable<Tuple3<String, byte[], String>>() {
                    @Override
                    public Tuple3<String, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, byte[], String>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static OpenseaInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OpenseaInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OpenseaInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OpenseaInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OpenseaInterface load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OpenseaInterface(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OpenseaInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OpenseaInterface(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OpenseaInterface> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OpenseaInterface.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OpenseaInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OpenseaInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<OpenseaInterface> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OpenseaInterface.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OpenseaInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OpenseaInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OfferItem extends StaticStruct {
        public BigInteger itemType;

        public String token;

        public BigInteger identifierOrCriteria;

        public BigInteger startAmount;

        public BigInteger endAmount;

        public OfferItem(BigInteger itemType, String token, BigInteger identifierOrCriteria, BigInteger startAmount, BigInteger endAmount) {
            super(new org.web3j.abi.datatypes.generated.Uint8(itemType), 
                    new org.web3j.abi.datatypes.Address(160, token), 
                    new org.web3j.abi.datatypes.generated.Uint256(identifierOrCriteria), 
                    new org.web3j.abi.datatypes.generated.Uint256(startAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(endAmount));
            this.itemType = itemType;
            this.token = token;
            this.identifierOrCriteria = identifierOrCriteria;
            this.startAmount = startAmount;
            this.endAmount = endAmount;
        }

        public OfferItem(Uint8 itemType, Address token, Uint256 identifierOrCriteria, Uint256 startAmount, Uint256 endAmount) {
            super(itemType, token, identifierOrCriteria, startAmount, endAmount);
            this.itemType = itemType.getValue();
            this.token = token.getValue();
            this.identifierOrCriteria = identifierOrCriteria.getValue();
            this.startAmount = startAmount.getValue();
            this.endAmount = endAmount.getValue();
        }
    }

    public static class ConsiderationItem extends StaticStruct {
        public BigInteger itemType;

        public String token;

        public BigInteger identifierOrCriteria;

        public BigInteger startAmount;

        public BigInteger endAmount;

        public String recipient;

        public ConsiderationItem(BigInteger itemType, String token, BigInteger identifierOrCriteria, BigInteger startAmount, BigInteger endAmount, String recipient) {
            super(new org.web3j.abi.datatypes.generated.Uint8(itemType), 
                    new org.web3j.abi.datatypes.Address(160, token), 
                    new org.web3j.abi.datatypes.generated.Uint256(identifierOrCriteria), 
                    new org.web3j.abi.datatypes.generated.Uint256(startAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(endAmount), 
                    new org.web3j.abi.datatypes.Address(160, recipient));
            this.itemType = itemType;
            this.token = token;
            this.identifierOrCriteria = identifierOrCriteria;
            this.startAmount = startAmount;
            this.endAmount = endAmount;
            this.recipient = recipient;
        }

        public ConsiderationItem(Uint8 itemType, Address token, Uint256 identifierOrCriteria, Uint256 startAmount, Uint256 endAmount, Address recipient) {
            super(itemType, token, identifierOrCriteria, startAmount, endAmount, recipient);
            this.itemType = itemType.getValue();
            this.token = token.getValue();
            this.identifierOrCriteria = identifierOrCriteria.getValue();
            this.startAmount = startAmount.getValue();
            this.endAmount = endAmount.getValue();
            this.recipient = recipient.getValue();
        }
    }

    public static class CriteriaResolver extends DynamicStruct {
        public BigInteger orderIndex;

        public BigInteger side;

        public BigInteger index;

        public BigInteger identifier;

        public List<byte[]> criteriaProof;

        public CriteriaResolver(BigInteger orderIndex, BigInteger side, BigInteger index, BigInteger identifier, List<byte[]> criteriaProof) {
            super(new org.web3j.abi.datatypes.generated.Uint256(orderIndex), 
                    new org.web3j.abi.datatypes.generated.Uint8(side), 
                    new org.web3j.abi.datatypes.generated.Uint256(index), 
                    new org.web3j.abi.datatypes.generated.Uint256(identifier), 
                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                            org.web3j.abi.datatypes.generated.Bytes32.class,
                            org.web3j.abi.Utils.typeMap(criteriaProof, org.web3j.abi.datatypes.generated.Bytes32.class)));
            this.orderIndex = orderIndex;
            this.side = side;
            this.index = index;
            this.identifier = identifier;
            this.criteriaProof = criteriaProof;
        }

        public CriteriaResolver(Uint256 orderIndex, Uint8 side, Uint256 index, Uint256 identifier, DynamicArray<Bytes32> criteriaProof) {
            super(orderIndex, side, index, identifier, criteriaProof);
            this.orderIndex = orderIndex.getValue();
            this.side = side.getValue();
            this.index = index.getValue();
            this.identifier = identifier.getValue();
            this.criteriaProof = criteriaProof.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
        }
    }

    public static class ReceivedItem extends StaticStruct {
        public BigInteger itemType;

        public String token;

        public BigInteger identifier;

        public BigInteger amount;

        public String recipient;

        public ReceivedItem(BigInteger itemType, String token, BigInteger identifier, BigInteger amount, String recipient) {
            super(new org.web3j.abi.datatypes.generated.Uint8(itemType), 
                    new org.web3j.abi.datatypes.Address(160, token), 
                    new org.web3j.abi.datatypes.generated.Uint256(identifier), 
                    new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.Address(160, recipient));
            this.itemType = itemType;
            this.token = token;
            this.identifier = identifier;
            this.amount = amount;
            this.recipient = recipient;
        }

        public ReceivedItem(Uint8 itemType, Address token, Uint256 identifier, Uint256 amount, Address recipient) {
            super(itemType, token, identifier, amount, recipient);
            this.itemType = itemType.getValue();
            this.token = token.getValue();
            this.identifier = identifier.getValue();
            this.amount = amount.getValue();
            this.recipient = recipient.getValue();
        }
    }

    public static class AdditionalRecipient extends StaticStruct {
        public BigInteger amount;

        public String recipient;

        public AdditionalRecipient(BigInteger amount, String recipient) {
            super(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.Address(160, recipient));
            this.amount = amount;
            this.recipient = recipient;
        }

        public AdditionalRecipient(Uint256 amount, Address recipient) {
            super(amount, recipient);
            this.amount = amount.getValue();
            this.recipient = recipient.getValue();
        }
    }

    public static class OrderParameters extends DynamicStruct {
        public String offerer;

        public String zone;

        public List<OfferItem> offer;

        public List<ConsiderationItem> consideration;

        public BigInteger orderType;

        public BigInteger startTime;

        public BigInteger endTime;

        public byte[] zoneHash;

        public BigInteger salt;

        public byte[] conduitKey;

        public BigInteger totalOriginalConsiderationItems;

        public OrderParameters(String offerer, String zone, List<OfferItem> offer, List<ConsiderationItem> consideration, BigInteger orderType, BigInteger startTime, BigInteger endTime, byte[] zoneHash, BigInteger salt, byte[] conduitKey, BigInteger totalOriginalConsiderationItems) {
            super(new org.web3j.abi.datatypes.Address(160, offerer), 
                    new org.web3j.abi.datatypes.Address(160, zone), 
                    new org.web3j.abi.datatypes.DynamicArray<OfferItem>(OfferItem.class, offer), 
                    new org.web3j.abi.datatypes.DynamicArray<ConsiderationItem>(ConsiderationItem.class, consideration), 
                    new org.web3j.abi.datatypes.generated.Uint8(orderType), 
                    new org.web3j.abi.datatypes.generated.Uint256(startTime), 
                    new org.web3j.abi.datatypes.generated.Uint256(endTime), 
                    new org.web3j.abi.datatypes.generated.Bytes32(zoneHash), 
                    new org.web3j.abi.datatypes.generated.Uint256(salt), 
                    new org.web3j.abi.datatypes.generated.Bytes32(conduitKey), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalOriginalConsiderationItems));
            this.offerer = offerer;
            this.zone = zone;
            this.offer = offer;
            this.consideration = consideration;
            this.orderType = orderType;
            this.startTime = startTime;
            this.endTime = endTime;
            this.zoneHash = zoneHash;
            this.salt = salt;
            this.conduitKey = conduitKey;
            this.totalOriginalConsiderationItems = totalOriginalConsiderationItems;
        }

        public OrderParameters(Address offerer, Address zone, @Parameterized(type = OfferItem.class) DynamicArray<OfferItem> offer, @Parameterized(type = ConsiderationItem.class) DynamicArray<ConsiderationItem> consideration, Uint8 orderType, Uint256 startTime, Uint256 endTime, Bytes32 zoneHash, Uint256 salt, Bytes32 conduitKey, Uint256 totalOriginalConsiderationItems) {
            super(offerer, zone, offer, consideration, orderType, startTime, endTime, zoneHash, salt, conduitKey, totalOriginalConsiderationItems);
            this.offerer = offerer.getValue();
            this.zone = zone.getValue();
            this.offer = offer.getValue();
            this.consideration = consideration.getValue();
            this.orderType = orderType.getValue();
            this.startTime = startTime.getValue();
            this.endTime = endTime.getValue();
            this.zoneHash = zoneHash.getValue();
            this.salt = salt.getValue();
            this.conduitKey = conduitKey.getValue();
            this.totalOriginalConsiderationItems = totalOriginalConsiderationItems.getValue();
        }
    }

    public static class Execution extends StaticStruct {
        public ReceivedItem item;

        public String offerer;

        public byte[] conduitKey;

        public Execution(ReceivedItem item, String offerer, byte[] conduitKey) {
            super(item, 
                    new org.web3j.abi.datatypes.Address(160, offerer), 
                    new org.web3j.abi.datatypes.generated.Bytes32(conduitKey));
            this.item = item;
            this.offerer = offerer;
            this.conduitKey = conduitKey;
        }

        public Execution(ReceivedItem item, Address offerer, Bytes32 conduitKey) {
            super(item, offerer, conduitKey);
            this.item = item;
            this.offerer = offerer.getValue();
            this.conduitKey = conduitKey.getValue();
        }
    }

    public static class BasicOrderParameters extends DynamicStruct {
        public String considerationToken;

        public BigInteger considerationIdentifier;

        public BigInteger considerationAmount;

        public String offerer;

        public String zone;

        public String offerToken;

        public BigInteger offerIdentifier;

        public BigInteger offerAmount;

        public BigInteger basicOrderType;

        public BigInteger startTime;

        public BigInteger endTime;

        public byte[] zoneHash;

        public BigInteger salt;

        public byte[] offererConduitKey;

        public byte[] fulfillerConduitKey;

        public BigInteger totalOriginalAdditionalRecipients;

        public List<AdditionalRecipient> additionalRecipients;

        public byte[] signature;

        public BasicOrderParameters(String considerationToken, BigInteger considerationIdentifier, BigInteger considerationAmount, String offerer, String zone, String offerToken, BigInteger offerIdentifier, BigInteger offerAmount, BigInteger basicOrderType, BigInteger startTime, BigInteger endTime, byte[] zoneHash, BigInteger salt, byte[] offererConduitKey, byte[] fulfillerConduitKey, BigInteger totalOriginalAdditionalRecipients, List<AdditionalRecipient> additionalRecipients, byte[] signature) {
            super(new org.web3j.abi.datatypes.Address(160, considerationToken), 
                    new org.web3j.abi.datatypes.generated.Uint256(considerationIdentifier), 
                    new org.web3j.abi.datatypes.generated.Uint256(considerationAmount), 
                    new org.web3j.abi.datatypes.Address(160, offerer), 
                    new org.web3j.abi.datatypes.Address(160, zone), 
                    new org.web3j.abi.datatypes.Address(160, offerToken), 
                    new org.web3j.abi.datatypes.generated.Uint256(offerIdentifier), 
                    new org.web3j.abi.datatypes.generated.Uint256(offerAmount), 
                    new org.web3j.abi.datatypes.generated.Uint8(basicOrderType), 
                    new org.web3j.abi.datatypes.generated.Uint256(startTime), 
                    new org.web3j.abi.datatypes.generated.Uint256(endTime), 
                    new org.web3j.abi.datatypes.generated.Bytes32(zoneHash), 
                    new org.web3j.abi.datatypes.generated.Uint256(salt), 
                    new org.web3j.abi.datatypes.generated.Bytes32(offererConduitKey), 
                    new org.web3j.abi.datatypes.generated.Bytes32(fulfillerConduitKey), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalOriginalAdditionalRecipients), 
                    new org.web3j.abi.datatypes.DynamicArray<AdditionalRecipient>(AdditionalRecipient.class, additionalRecipients), 
                    new org.web3j.abi.datatypes.DynamicBytes(signature));
            this.considerationToken = considerationToken;
            this.considerationIdentifier = considerationIdentifier;
            this.considerationAmount = considerationAmount;
            this.offerer = offerer;
            this.zone = zone;
            this.offerToken = offerToken;
            this.offerIdentifier = offerIdentifier;
            this.offerAmount = offerAmount;
            this.basicOrderType = basicOrderType;
            this.startTime = startTime;
            this.endTime = endTime;
            this.zoneHash = zoneHash;
            this.salt = salt;
            this.offererConduitKey = offererConduitKey;
            this.fulfillerConduitKey = fulfillerConduitKey;
            this.totalOriginalAdditionalRecipients = totalOriginalAdditionalRecipients;
            this.additionalRecipients = additionalRecipients;
            this.signature = signature;
        }

        public BasicOrderParameters(Address considerationToken, Uint256 considerationIdentifier, Uint256 considerationAmount, Address offerer, Address zone, Address offerToken, Uint256 offerIdentifier, Uint256 offerAmount, Uint8 basicOrderType, Uint256 startTime, Uint256 endTime, Bytes32 zoneHash, Uint256 salt, Bytes32 offererConduitKey, Bytes32 fulfillerConduitKey, Uint256 totalOriginalAdditionalRecipients, @Parameterized(type = AdditionalRecipient.class) DynamicArray<AdditionalRecipient> additionalRecipients, DynamicBytes signature) {
            super(considerationToken, considerationIdentifier, considerationAmount, offerer, zone, offerToken, offerIdentifier, offerAmount, basicOrderType, startTime, endTime, zoneHash, salt, offererConduitKey, fulfillerConduitKey, totalOriginalAdditionalRecipients, additionalRecipients, signature);
            this.considerationToken = considerationToken.getValue();
            this.considerationIdentifier = considerationIdentifier.getValue();
            this.considerationAmount = considerationAmount.getValue();
            this.offerer = offerer.getValue();
            this.zone = zone.getValue();
            this.offerToken = offerToken.getValue();
            this.offerIdentifier = offerIdentifier.getValue();
            this.offerAmount = offerAmount.getValue();
            this.basicOrderType = basicOrderType.getValue();
            this.startTime = startTime.getValue();
            this.endTime = endTime.getValue();
            this.zoneHash = zoneHash.getValue();
            this.salt = salt.getValue();
            this.offererConduitKey = offererConduitKey.getValue();
            this.fulfillerConduitKey = fulfillerConduitKey.getValue();
            this.totalOriginalAdditionalRecipients = totalOriginalAdditionalRecipients.getValue();
            this.additionalRecipients = additionalRecipients.getValue();
            this.signature = signature.getValue();
        }
    }

    public static class AdvancedOrder extends DynamicStruct {
        public OrderParameters parameters;

        public BigInteger numerator;

        public BigInteger denominator;

        public byte[] signature;

        public byte[] extraData;

        public AdvancedOrder(OrderParameters parameters, BigInteger numerator, BigInteger denominator, byte[] signature, byte[] extraData) {
            super(parameters, 
                    new org.web3j.abi.datatypes.generated.Uint120(numerator), 
                    new org.web3j.abi.datatypes.generated.Uint120(denominator), 
                    new org.web3j.abi.datatypes.DynamicBytes(signature), 
                    new org.web3j.abi.datatypes.DynamicBytes(extraData));
            this.parameters = parameters;
            this.numerator = numerator;
            this.denominator = denominator;
            this.signature = signature;
            this.extraData = extraData;
        }

        public AdvancedOrder(OrderParameters parameters, Uint120 numerator, Uint120 denominator, DynamicBytes signature, DynamicBytes extraData) {
            super(parameters, numerator, denominator, signature, extraData);
            this.parameters = parameters;
            this.numerator = numerator.getValue();
            this.denominator = denominator.getValue();
            this.signature = signature.getValue();
            this.extraData = extraData.getValue();
        }
    }
}
