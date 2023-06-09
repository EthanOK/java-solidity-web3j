package org.web3j.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
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
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class NFTExchangeLog extends Contract {
    public static final String BINARY = "6080604052348015600f57600080fd5b50603f80601d6000396000f3fe6080604052600080fdfea2646970667358221220cd25e3ba287ef591345179030f315cbb00d38db6e10391ded37f947851e5dcf964736f6c63430008110033";

    public static final Event EXCHANGE_EVENT = new Event("Exchange", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected NFTExchangeLog(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFTExchangeLog(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFTExchangeLog(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFTExchangeLog(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ExchangeEventResponse> getExchangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(EXCHANGE_EVENT, transactionReceipt);
        ArrayList<ExchangeEventResponse> responses = new ArrayList<ExchangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ExchangeEventResponse typedResponse = new ExchangeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sellToken = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sellTokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sellAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.unitPrice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyToken = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.buyTokenId = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.payPrice = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.royaltyFee = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ExchangeEventResponse getExchangeEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(EXCHANGE_EVENT, log);
        ExchangeEventResponse typedResponse = new ExchangeEventResponse();
        typedResponse.log = log;
        typedResponse.sellToken = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.sellTokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.sellAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.unitPrice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.buyToken = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.buyTokenId = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
        typedResponse.payPrice = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
        typedResponse.royaltyFee = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
        return typedResponse;
    }

    public Flowable<ExchangeEventResponse> exchangeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getExchangeEventFromLog(log));
    }

    public Flowable<ExchangeEventResponse> exchangeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EXCHANGE_EVENT));
        return exchangeEventFlowable(filter);
    }

    @Deprecated
    public static NFTExchangeLog load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTExchangeLog(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTExchangeLog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTExchangeLog(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFTExchangeLog load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFTExchangeLog(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFTExchangeLog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFTExchangeLog(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFTExchangeLog> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTExchangeLog.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTExchangeLog> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTExchangeLog.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<NFTExchangeLog> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTExchangeLog.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTExchangeLog> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTExchangeLog.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ExchangeEventResponse extends BaseEventResponse {
        public String sellToken;

        public BigInteger sellTokenId;

        public BigInteger sellAmount;

        public BigInteger unitPrice;

        public String seller;

        public String buyToken;

        public BigInteger buyTokenId;

        public String buyer;

        public BigInteger amount;

        public BigInteger payPrice;

        public BigInteger royaltyFee;
    }
}
