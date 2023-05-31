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
public class YgmeStakingDomain extends Contract {
    public static final String BINARY = "";

    public static final Event STAKING_EVENT = new Event("Staking", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWERC20_EVENT = new Event("WithdrawERC20", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected YgmeStakingDomain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected YgmeStakingDomain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected YgmeStakingDomain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected YgmeStakingDomain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<StakingEventResponse> getStakingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKING_EVENT, transactionReceipt);
        ArrayList<StakingEventResponse> responses = new ArrayList<StakingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StakingEventResponse typedResponse = new StakingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.nftContract = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.startTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.endTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.pledgeType = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakingEventResponse getStakingEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKING_EVENT, log);
        StakingEventResponse typedResponse = new StakingEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.nftContract = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.startTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.endTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.pledgeType = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<StakingEventResponse> stakingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakingEventFromLog(log));
    }

    public Flowable<StakingEventResponse> stakingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKING_EVENT));
        return stakingEventFlowable(filter);
    }

    public static List<WithdrawERC20EventResponse> getWithdrawERC20Events(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WITHDRAWERC20_EVENT, transactionReceipt);
        ArrayList<WithdrawERC20EventResponse> responses = new ArrayList<WithdrawERC20EventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawERC20EventResponse typedResponse = new WithdrawERC20EventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WithdrawERC20EventResponse getWithdrawERC20EventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WITHDRAWERC20_EVENT, log);
        WithdrawERC20EventResponse typedResponse = new WithdrawERC20EventResponse();
        typedResponse.log = log;
        typedResponse.orderId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.account = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<WithdrawERC20EventResponse> withdrawERC20EventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWithdrawERC20EventFromLog(log));
    }

    public Flowable<WithdrawERC20EventResponse> withdrawERC20EventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWERC20_EVENT));
        return withdrawERC20EventFlowable(filter);
    }

    @Deprecated
    public static YgmeStakingDomain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new YgmeStakingDomain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static YgmeStakingDomain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new YgmeStakingDomain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static YgmeStakingDomain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new YgmeStakingDomain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static YgmeStakingDomain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new YgmeStakingDomain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<YgmeStakingDomain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(YgmeStakingDomain.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<YgmeStakingDomain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YgmeStakingDomain.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<YgmeStakingDomain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(YgmeStakingDomain.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<YgmeStakingDomain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YgmeStakingDomain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class StakingEventResponse extends BaseEventResponse {
        public String account;

        public BigInteger tokenId;

        public String nftContract;

        public BigInteger startTime;

        public BigInteger endTime;

        public BigInteger pledgeType;
    }

    public static class WithdrawERC20EventResponse extends BaseEventResponse {
        public BigInteger orderId;

        public String account;

        public BigInteger amount;
    }
}
