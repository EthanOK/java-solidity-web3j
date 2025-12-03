package org.web3j.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class IBSwapAggregator extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_SWAP = "swap";

    public static final String FUNC_SWAPEXACTINPUT = "swapExactInput";

    public static final String FUNC_SWAPEXACTINPUTWITHPERMIT = "swapExactInputWithPermit";

    public static final String FUNC_SWAPEXACTINPUTWITHPERMIT2 = "swapExactInputWithPermit2";

    public static final Event SWAPEXACTEVENT_EVENT = new Event("SwapExactEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SWAPSINGLE_EVENT = new Event("SwapSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IBSwapAggregator(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IBSwapAggregator(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IBSwapAggregator(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IBSwapAggregator(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<SwapExactEventEventResponse> getSwapExactEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SWAPEXACTEVENT_EVENT, transactionReceipt);
        ArrayList<SwapExactEventEventResponse> responses = new ArrayList<SwapExactEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SwapExactEventEventResponse typedResponse = new SwapExactEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.swapType = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenIn = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenOut = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amountIn = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amountOut = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SwapExactEventEventResponse getSwapExactEventEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SWAPEXACTEVENT_EVENT, log);
        SwapExactEventEventResponse typedResponse = new SwapExactEventEventResponse();
        typedResponse.log = log;
        typedResponse.swapType = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenIn = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.tokenOut = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amountIn = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.amountOut = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<SwapExactEventEventResponse> swapExactEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSwapExactEventEventFromLog(log));
    }

    public Flowable<SwapExactEventEventResponse> swapExactEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SWAPEXACTEVENT_EVENT));
        return swapExactEventEventFlowable(filter);
    }

    public static List<SwapSingleEventResponse> getSwapSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SWAPSINGLE_EVENT, transactionReceipt);
        ArrayList<SwapSingleEventResponse> responses = new ArrayList<SwapSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SwapSingleEventResponse typedResponse = new SwapSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.swapType = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenIn = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amountIn = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SwapSingleEventResponse getSwapSingleEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SWAPSINGLE_EVENT, log);
        SwapSingleEventResponse typedResponse = new SwapSingleEventResponse();
        typedResponse.log = log;
        typedResponse.swapType = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenIn = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amountIn = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<SwapSingleEventResponse> swapSingleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSwapSingleEventFromLog(log));
    }

    public Flowable<SwapSingleEventResponse> swapSingleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SWAPSINGLE_EVENT));
        return swapSingleEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> swap(String tokenIn, BigInteger amountIn, SwapData swapData, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, tokenIn), 
                new org.web3j.abi.datatypes.generated.Uint256(amountIn), 
                swapData), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactInput(SwapDataInfo swapDataInfo, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPEXACTINPUT, 
                Arrays.<Type>asList(swapDataInfo), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactInputWithPermit(SwapDataInfo swapDataInfo, BigInteger deadline, byte[] signature, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPEXACTINPUTWITHPERMIT, 
                Arrays.<Type>asList(swapDataInfo, 
                new org.web3j.abi.datatypes.generated.Uint256(deadline), 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactInputWithPermit2(SwapDataInfo swapDataInfo, BigInteger nonce, BigInteger deadline, byte[] signature, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPEXACTINPUTWITHPERMIT2, 
                Arrays.<Type>asList(swapDataInfo, 
                new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                new org.web3j.abi.datatypes.generated.Uint256(deadline), 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static IBSwapAggregator load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBSwapAggregator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IBSwapAggregator load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBSwapAggregator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IBSwapAggregator load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IBSwapAggregator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IBSwapAggregator load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IBSwapAggregator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IBSwapAggregator> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBSwapAggregator.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IBSwapAggregator> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBSwapAggregator.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IBSwapAggregator> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBSwapAggregator.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IBSwapAggregator> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBSwapAggregator.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SwapData extends DynamicStruct {
        public BigInteger swapType;

        public String extRouter;

        public byte[] extCalldata;

        public Boolean needScale;

        public SwapData(BigInteger swapType, String extRouter, byte[] extCalldata, Boolean needScale) {
            super(new org.web3j.abi.datatypes.generated.Uint8(swapType), 
                    new org.web3j.abi.datatypes.Address(160, extRouter), 
                    new org.web3j.abi.datatypes.DynamicBytes(extCalldata), 
                    new org.web3j.abi.datatypes.Bool(needScale));
            this.swapType = swapType;
            this.extRouter = extRouter;
            this.extCalldata = extCalldata;
            this.needScale = needScale;
        }

        public SwapData(Uint8 swapType, Address extRouter, DynamicBytes extCalldata, Bool needScale) {
            super(swapType, extRouter, extCalldata, needScale);
            this.swapType = swapType.getValue();
            this.extRouter = extRouter.getValue();
            this.extCalldata = extCalldata.getValue();
            this.needScale = needScale.getValue();
        }
    }

    public static class SwapDataInfo extends DynamicStruct {
        public String tokenIn;

        public BigInteger amountIn;

        public String tokenOut;

        public BigInteger minOut;

        public String receiver;

        public SwapData swapData;

        public SwapDataInfo(String tokenIn, BigInteger amountIn, String tokenOut, BigInteger minOut, String receiver, SwapData swapData) {
            super(new org.web3j.abi.datatypes.Address(160, tokenIn), 
                    new org.web3j.abi.datatypes.generated.Uint256(amountIn), 
                    new org.web3j.abi.datatypes.Address(160, tokenOut), 
                    new org.web3j.abi.datatypes.generated.Uint256(minOut), 
                    new org.web3j.abi.datatypes.Address(160, receiver), 
                    swapData);
            this.tokenIn = tokenIn;
            this.amountIn = amountIn;
            this.tokenOut = tokenOut;
            this.minOut = minOut;
            this.receiver = receiver;
            this.swapData = swapData;
        }

        public SwapDataInfo(Address tokenIn, Uint256 amountIn, Address tokenOut, Uint256 minOut, Address receiver, SwapData swapData) {
            super(tokenIn, amountIn, tokenOut, minOut, receiver, swapData);
            this.tokenIn = tokenIn.getValue();
            this.amountIn = amountIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.minOut = minOut.getValue();
            this.receiver = receiver.getValue();
            this.swapData = swapData;
        }
    }

    public static class SwapExactEventEventResponse extends BaseEventResponse {
        public BigInteger swapType;

        public String tokenIn;

        public String tokenOut;

        public String sender;

        public BigInteger amountIn;

        public String receiver;

        public BigInteger amountOut;
    }

    public static class SwapSingleEventResponse extends BaseEventResponse {
        public BigInteger swapType;

        public String tokenIn;

        public BigInteger amountIn;
    }
}
