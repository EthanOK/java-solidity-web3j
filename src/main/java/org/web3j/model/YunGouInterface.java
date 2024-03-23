package org.web3j.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint120;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
 * <p>Generated with web3j version 4.11.0.
 */
@SuppressWarnings("rawtypes")
public class YunGouInterface extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_BATCHEXCUTEWITHETH = "batchExcuteWithETH";

    public static final String FUNC_CANCEL = "cancel";

    public static final String FUNC_EXCUTEWITHETH = "excuteWithETH";

    public static final String FUNC_GETBENEFICIARY = "getBeneficiary";

    public static final String FUNC_GETORDERHASH = "getOrderHash";

    public static final String FUNC_GETORDERSTATUS = "getOrderStatus";

    public static final String FUNC_GETSYSTEMVERIFIER = "getSystemVerifier";

    public static final String FUNC_INFORMATION = "information";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_REMOVEORDERHASHS = "removeOrderHashs";

    public static final String FUNC_SETBENEFICIARY = "setBeneficiary";

    public static final String FUNC_SETPAUSE = "setPause";

    public static final String FUNC_SETSYSTEMVERIFIER = "setSystemVerifier";

    public static final Event ORDERCANCELLED_EVENT = new Event("OrderCancelled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ORDERFULFILLED_EVENT = new Event("OrderFulfilled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected YunGouInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected YunGouInterface(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected YunGouInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected YunGouInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<OrderCancelledEventResponse> getOrderCancelledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORDERCANCELLED_EVENT, transactionReceipt);
        ArrayList<OrderCancelledEventResponse> responses = new ArrayList<OrderCancelledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrderCancelledEventResponse typedResponse = new OrderCancelledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orderHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrderCancelledEventResponse getOrderCancelledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORDERCANCELLED_EVENT, log);
        OrderCancelledEventResponse typedResponse = new OrderCancelledEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.orderHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OrderCancelledEventResponse> orderCancelledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrderCancelledEventFromLog(log));
    }

    public Flowable<OrderCancelledEventResponse> orderCancelledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORDERCANCELLED_EVENT));
        return orderCancelledEventFlowable(filter);
    }

    public static List<OrderFulfilledEventResponse> getOrderFulfilledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORDERFULFILLED_EVENT, transactionReceipt);
        ArrayList<OrderFulfilledEventResponse> responses = new ArrayList<OrderFulfilledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrderFulfilledEventResponse typedResponse = new OrderFulfilledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.offerer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.offerToken = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.buyer = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.orderHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.offerTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.buyAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.totalPayment = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.totalRoyaltyFee = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.totalPlatformFee = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrderFulfilledEventResponse getOrderFulfilledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORDERFULFILLED_EVENT, log);
        OrderFulfilledEventResponse typedResponse = new OrderFulfilledEventResponse();
        typedResponse.log = log;
        typedResponse.offerer = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.offerToken = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.buyer = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.orderHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.offerTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.buyAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.totalPayment = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.totalRoyaltyFee = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.totalPlatformFee = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<OrderFulfilledEventResponse> orderFulfilledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrderFulfilledEventFromLog(log));
    }

    public Flowable<OrderFulfilledEventResponse> orderFulfilledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORDERFULFILLED_EVENT));
        return orderFulfilledEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> batchExcuteWithETH(List<BasicOrder> orders, String receiver, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BATCHEXCUTEWITHETH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<BasicOrder>(BasicOrder.class, orders), 
                new org.web3j.abi.datatypes.Address(160, receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cancel(List<BasicOrderParameters> ordersParameters) {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<BasicOrderParameters>(BasicOrderParameters.class, ordersParameters)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> excuteWithETH(BasicOrder order, String receiver, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXCUTEWITHETH, 
                Arrays.<Type>asList(order, 
                new org.web3j.abi.datatypes.Address(160, receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> getBeneficiary() {
        final Function function = new Function(FUNC_GETBENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> getOrderHash(BasicOrderParameters orderParameters) {
        final Function function = new Function(FUNC_GETORDERHASH, 
                Arrays.<Type>asList(orderParameters), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<OrderStatus> getOrderStatus(byte[] orderHash) {
        final Function function = new Function(FUNC_GETORDERSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(orderHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<OrderStatus>() {}));
        return executeRemoteCallSingleValueReturn(function, OrderStatus.class);
    }

    public RemoteFunctionCall<String> getSystemVerifier() {
        final Function function = new Function(FUNC_GETSYSTEMVERIFIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, byte[]>> information() {
        final Function function = new Function(FUNC_INFORMATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple2<String, byte[]>>(function,
                new Callable<Tuple2<String, byte[]>>() {
                    @Override
                    public Tuple2<String, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, byte[]>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOrderHashs(List<byte[]> orderHashs) {
        final Function function = new Function(
                FUNC_REMOVEORDERHASHS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(orderHashs, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBeneficiary(String newBeneficiary) {
        final Function function = new Function(
                FUNC_SETBENEFICIARY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newBeneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPause() {
        final Function function = new Function(
                FUNC_SETPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSystemVerifier(String _systemVerifier) {
        final Function function = new Function(
                FUNC_SETSYSTEMVERIFIER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _systemVerifier)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static YunGouInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new YunGouInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static YunGouInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new YunGouInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static YunGouInterface load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new YunGouInterface(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static YunGouInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new YunGouInterface(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<YunGouInterface> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(YunGouInterface.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<YunGouInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YunGouInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<YunGouInterface> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(YunGouInterface.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<YunGouInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YunGouInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class BasicOrderParameters extends StaticStruct {
        public BigInteger orderType;

        public String offerer;

        public String offerToken;

        public BigInteger offerTokenId;

        public BigInteger unitPrice;

        public BigInteger sellAmount;

        public BigInteger startTime;

        public BigInteger endTime;

        public String paymentToken;

        public BigInteger paymentTokenId;

        public BigInteger salt;

        public BigInteger royaltyFee;

        public BigInteger platformFee;

        public BigInteger afterTaxPrice;

        public BasicOrderParameters(BigInteger orderType, String offerer, String offerToken, BigInteger offerTokenId, BigInteger unitPrice, BigInteger sellAmount, BigInteger startTime, BigInteger endTime, String paymentToken, BigInteger paymentTokenId, BigInteger salt, BigInteger royaltyFee, BigInteger platformFee, BigInteger afterTaxPrice) {
            super(new org.web3j.abi.datatypes.generated.Uint8(orderType), 
                    new org.web3j.abi.datatypes.Address(160, offerer), 
                    new org.web3j.abi.datatypes.Address(160, offerToken), 
                    new org.web3j.abi.datatypes.generated.Uint256(offerTokenId), 
                    new org.web3j.abi.datatypes.generated.Uint256(unitPrice), 
                    new org.web3j.abi.datatypes.generated.Uint256(sellAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(startTime), 
                    new org.web3j.abi.datatypes.generated.Uint256(endTime), 
                    new org.web3j.abi.datatypes.Address(160, paymentToken), 
                    new org.web3j.abi.datatypes.generated.Uint256(paymentTokenId), 
                    new org.web3j.abi.datatypes.generated.Uint256(salt), 
                    new org.web3j.abi.datatypes.generated.Uint256(royaltyFee), 
                    new org.web3j.abi.datatypes.generated.Uint256(platformFee), 
                    new org.web3j.abi.datatypes.generated.Uint256(afterTaxPrice));
            this.orderType = orderType;
            this.offerer = offerer;
            this.offerToken = offerToken;
            this.offerTokenId = offerTokenId;
            this.unitPrice = unitPrice;
            this.sellAmount = sellAmount;
            this.startTime = startTime;
            this.endTime = endTime;
            this.paymentToken = paymentToken;
            this.paymentTokenId = paymentTokenId;
            this.salt = salt;
            this.royaltyFee = royaltyFee;
            this.platformFee = platformFee;
            this.afterTaxPrice = afterTaxPrice;
        }

        public BasicOrderParameters(Uint8 orderType, Address offerer, Address offerToken, Uint256 offerTokenId, Uint256 unitPrice, Uint256 sellAmount, Uint256 startTime, Uint256 endTime, Address paymentToken, Uint256 paymentTokenId, Uint256 salt, Uint256 royaltyFee, Uint256 platformFee, Uint256 afterTaxPrice) {
            super(orderType, offerer, offerToken, offerTokenId, unitPrice, sellAmount, startTime, endTime, paymentToken, paymentTokenId, salt, royaltyFee, platformFee, afterTaxPrice);
            this.orderType = orderType.getValue();
            this.offerer = offerer.getValue();
            this.offerToken = offerToken.getValue();
            this.offerTokenId = offerTokenId.getValue();
            this.unitPrice = unitPrice.getValue();
            this.sellAmount = sellAmount.getValue();
            this.startTime = startTime.getValue();
            this.endTime = endTime.getValue();
            this.paymentToken = paymentToken.getValue();
            this.paymentTokenId = paymentTokenId.getValue();
            this.salt = salt.getValue();
            this.royaltyFee = royaltyFee.getValue();
            this.platformFee = platformFee.getValue();
            this.afterTaxPrice = afterTaxPrice.getValue();
        }
    }

    public static class OrderStatus extends StaticStruct {
        public Boolean isValidated;

        public Boolean isCancelled;

        public BigInteger soldTotal;

        public BigInteger shelvesTotal;

        public OrderStatus(Boolean isValidated, Boolean isCancelled, BigInteger soldTotal, BigInteger shelvesTotal) {
            super(new org.web3j.abi.datatypes.Bool(isValidated), 
                    new org.web3j.abi.datatypes.Bool(isCancelled), 
                    new org.web3j.abi.datatypes.generated.Uint120(soldTotal), 
                    new org.web3j.abi.datatypes.generated.Uint120(shelvesTotal));
            this.isValidated = isValidated;
            this.isCancelled = isCancelled;
            this.soldTotal = soldTotal;
            this.shelvesTotal = shelvesTotal;
        }

        public OrderStatus(Bool isValidated, Bool isCancelled, Uint120 soldTotal, Uint120 shelvesTotal) {
            super(isValidated, isCancelled, soldTotal, shelvesTotal);
            this.isValidated = isValidated.getValue();
            this.isCancelled = isCancelled.getValue();
            this.soldTotal = soldTotal.getValue();
            this.shelvesTotal = shelvesTotal.getValue();
        }
    }

    public static class BasicOrder extends DynamicStruct {
        public BasicOrderParameters parameters;

        public byte[] orderSignature;

        public BigInteger buyAmount;

        public BigInteger totalRoyaltyFee;

        public BigInteger totalPlatformFee;

        public BigInteger totalAfterTaxIncome;

        public BigInteger totalPayment;

        public BigInteger expiryDate;

        public byte[] systemSignature;

        public BasicOrder(BasicOrderParameters parameters, byte[] orderSignature, BigInteger buyAmount, BigInteger totalRoyaltyFee, BigInteger totalPlatformFee, BigInteger totalAfterTaxIncome, BigInteger totalPayment, BigInteger expiryDate, byte[] systemSignature) {
            super(parameters, 
                    new org.web3j.abi.datatypes.DynamicBytes(orderSignature), 
                    new org.web3j.abi.datatypes.generated.Uint256(buyAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalRoyaltyFee), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalPlatformFee), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalAfterTaxIncome), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalPayment), 
                    new org.web3j.abi.datatypes.generated.Uint256(expiryDate), 
                    new org.web3j.abi.datatypes.DynamicBytes(systemSignature));
            this.parameters = parameters;
            this.orderSignature = orderSignature;
            this.buyAmount = buyAmount;
            this.totalRoyaltyFee = totalRoyaltyFee;
            this.totalPlatformFee = totalPlatformFee;
            this.totalAfterTaxIncome = totalAfterTaxIncome;
            this.totalPayment = totalPayment;
            this.expiryDate = expiryDate;
            this.systemSignature = systemSignature;
        }

        public BasicOrder(BasicOrderParameters parameters, DynamicBytes orderSignature, Uint256 buyAmount, Uint256 totalRoyaltyFee, Uint256 totalPlatformFee, Uint256 totalAfterTaxIncome, Uint256 totalPayment, Uint256 expiryDate, DynamicBytes systemSignature) {
            super(parameters, orderSignature, buyAmount, totalRoyaltyFee, totalPlatformFee, totalAfterTaxIncome, totalPayment, expiryDate, systemSignature);
            this.parameters = parameters;
            this.orderSignature = orderSignature.getValue();
            this.buyAmount = buyAmount.getValue();
            this.totalRoyaltyFee = totalRoyaltyFee.getValue();
            this.totalPlatformFee = totalPlatformFee.getValue();
            this.totalAfterTaxIncome = totalAfterTaxIncome.getValue();
            this.totalPayment = totalPayment.getValue();
            this.expiryDate = expiryDate.getValue();
            this.systemSignature = systemSignature.getValue();
        }
    }

    public static class OrderCancelledEventResponse extends BaseEventResponse {
        public String account;

        public byte[] orderHash;
    }

    public static class OrderFulfilledEventResponse extends BaseEventResponse {
        public String offerer;

        public String offerToken;

        public String buyer;

        public byte[] orderHash;

        public BigInteger offerTokenId;

        public BigInteger buyAmount;

        public BigInteger totalPayment;

        public BigInteger totalRoyaltyFee;

        public BigInteger totalPlatformFee;
    }
}
