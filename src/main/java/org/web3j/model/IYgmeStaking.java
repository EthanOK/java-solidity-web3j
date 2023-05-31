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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.StaticArray3;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
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
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class IYgmeStaking extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_AGGREGATESTATICCALL = "aggregateStaticCall";

    public static final String FUNC_GETSTAKINGPERIODS = "getStakingPeriods";

    public static final String FUNC_GETSTAKINGTOKENIDS = "getStakingTokenIds";

    public static final String FUNC_GETWITHDRAWSIGNER = "getWithdrawSigner";

    public static final String FUNC_ORDERISINVALID = "orderIsInvalid";

    public static final String FUNC_SETOPERATOR = "setOperator";

    public static final String FUNC_SETPAUSE = "setPause";

    public static final String FUNC_SETSTAKINGPERIODS = "setStakingPeriods";

    public static final String FUNC_SETWITHDRAWSIGNER = "setWithdrawSigner";

    public static final String FUNC_STAKING = "staking";

    public static final String FUNC_UNSTAKE = "unStake";

    public static final String FUNC_WITHDRAWERC20 = "withdrawERC20";

    public static final Event STAKING_EVENT = new Event("Staking", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWERC20_EVENT = new Event("WithdrawERC20", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IYgmeStaking(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IYgmeStaking(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IYgmeStaking(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IYgmeStaking(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<Tuple2<BigInteger, List<byte[]>>> aggregateStaticCall(List<Call> calls) {
        final Function function = new Function(FUNC_AGGREGATESTATICCALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<DynamicBytes>>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, List<byte[]>>>(function,
                new Callable<Tuple2<BigInteger, List<byte[]>>>() {
                    @Override
                    public Tuple2<BigInteger, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, List<byte[]>>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<DynamicBytes>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<List> getStakingPeriods() {
        final Function function = new Function(FUNC_GETSTAKINGPERIODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint64>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getStakingTokenIds(String _account) {
        final Function function = new Function(FUNC_GETSTAKINGTOKENIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getWithdrawSigner() {
        final Function function = new Function(FUNC_GETWITHDRAWSIGNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> orderIsInvalid(BigInteger orderId) {
        final Function function = new Function(FUNC_ORDERISINVALID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setOperator(String _account) {
        final Function function = new Function(
                FUNC_SETOPERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
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

    public RemoteFunctionCall<TransactionReceipt> setStakingPeriods(List<BigInteger> _periods) {
        final Function function = new Function(
                FUNC_SETSTAKINGPERIODS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.StaticArray3<org.web3j.abi.datatypes.generated.Uint64>(
                        org.web3j.abi.datatypes.generated.Uint64.class,
                        org.web3j.abi.Utils.typeMap(_periods, org.web3j.abi.datatypes.generated.Uint64.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWithdrawSigner(String _withdrawSigner) {
        final Function function = new Function(
                FUNC_SETWITHDRAWSIGNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _withdrawSigner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> staking(List<BigInteger> _tokenIds, BigInteger _stakeTime) {
        final Function function = new Function(
                FUNC_STAKING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_stakeTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unStake(List<BigInteger> _tokenIds) {
        final Function function = new Function(
                FUNC_UNSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawERC20(byte[] data, byte[] signature) {
        final Function function = new Function(
                FUNC_WITHDRAWERC20, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IYgmeStaking load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IYgmeStaking(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IYgmeStaking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IYgmeStaking(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IYgmeStaking load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IYgmeStaking(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IYgmeStaking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IYgmeStaking(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IYgmeStaking.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IYgmeStaking.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IYgmeStaking.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IYgmeStaking.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Call extends DynamicStruct {
        public String target;

        public byte[] callData;

        public Call(String target, byte[] callData) {
            super(new org.web3j.abi.datatypes.Address(160, target), 
                    new org.web3j.abi.datatypes.DynamicBytes(callData));
            this.target = target;
            this.callData = callData;
        }

        public Call(Address target, DynamicBytes callData) {
            super(target, callData);
            this.target = target.getValue();
            this.callData = callData.getValue();
        }
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
