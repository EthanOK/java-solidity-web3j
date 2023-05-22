package org.web3j.model;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.StaticArray3;
import org.web3j.abi.datatypes.generated.Uint128;
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
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class YgmeStaking extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516107be3803806107be8339818101604052606081101561003357600080fd5b505061077a806100446000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c8063888a8d0a11610097578063d431b1ac11610066578063d431b1ac14610465578063efff2ae51461046d578063f8c4bbf21461048a578063fa234a5214610492576100f5565b8063888a8d0a146103245780638c181f0014610392578063b10dcc93146103d2578063d11c31e814610440576100f5565b806340edabeb116100d357806340edabeb1461024657806341ad2077146102645780636232d83f146102da5780637ccbfe7c146102fe576100f5565b80631738ad86146100fa578063243e0ab01461015057806324ca02a314610222575b600080fd5b6101176004803603602081101561011057600080fd5b5035610500565b604080516001600160a01b03909516855292151560208501526001600160801b0391821684840152166060830152519081900360800190f35b61020e6004803603604081101561016657600080fd5b810190602081018135600160201b81111561018057600080fd5b82018360208201111561019257600080fd5b803590602001918460018302840111600160201b831117156101b357600080fd5b919390929091602081019035600160201b8111156101d057600080fd5b8201836020820111156101e257600080fd5b803590602001918460018302840111600160201b8311171561020357600080fd5b509092509050610543565b604080519115158252519081900360200190f35b61022a61054d565b604080516001600160801b039092168252519081900360200190f35b6102626004803603606081101561025c57600080fd5b50610563565b005b61028a6004803603602081101561027a57600080fd5b50356001600160a01b03166105de565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156102c65781810151838201526020016102ae565b505050509050019250505060405180910390f35b6102e261064a565b604080516001600160a01b039092168252519081900360200190f35b6102626004803603602081101561031457600080fd5b50356001600160a01b0316610659565b6102626004803603602081101561033a57600080fd5b810190602081018135600160201b81111561035457600080fd5b82018360208201111561036657600080fd5b803590602001918460208302840111600160201b8311171561038757600080fd5b5090925090506105da565b61039a61067b565b6040518082606080838360005b838110156103bf5781810151838201526020016103a7565b5050505090500191505060405180910390f35b61020e600480360360208110156103e857600080fd5b810190602081018135600160201b81111561040257600080fd5b82018360208201111561041457600080fd5b803590602001918460208302840111600160201b8311171561043557600080fd5b5090925090506106e8565b6104486106f0565b6040805167ffffffffffffffff9092168252519081900360200190f35b6102626106f7565b61020e6004803603602081101561048357600080fd5b50356106f9565b61022a61070e565b61020e600480360360408110156104a857600080fd5b810190602081018135600160201b8111156104c257600080fd5b8201836020820111156104d457600080fd5b803590602001918460208302840111600160201b831117156104f557600080fd5b91935091503561071d565b600260205260009081526040902080546001909101546001600160a01b03821691600160a01b900460ff16906001600160801b0380821691600160801b90041684565b6001949350505050565b600554600160801b90046001600160801b031681565b60005b60038110156105da576201518082826003811061057f57fe5b602002013567ffffffffffffffff16026000826003811061059c57fe5b600491828204019190066008026101000a81548167ffffffffffffffff021916908367ffffffffffffffff1602179055508080600101915050610566565b5050565b6001600160a01b03811660009081526003602090815260409182902080548351818402810184019094528084526060939283018282801561063e57602002820191906000526020600020905b81548152602001906001019080831161062a575b50505050509050919050565b6001546001600160a01b031690565b600180546001600160a01b0319166001600160a01b0392909216919091179055565b610683610726565b604080516060810191829052906000906003908280855b82829054906101000a900467ffffffffffffffff1667ffffffffffffffff168152602001906008019060208260070104928301926001038202915080841161069a5790505050505050905090565b600192915050565b6201518081565b565b60046020526000908152604090205460ff1681565b6005546001600160801b031681565b60019392505050565b6040518060600160405280600390602082028036833750919291505056fea2646970667358221220e2349f646b873cf5ea5f0a801cf94af0d38118baa88c2b3bd997b1615e8c4b1264736f6c63430007010033";

    public static final String FUNC_ONE_CYCLE = "ONE_CYCLE";

    public static final String FUNC_ACCOUNTTOTAL = "accountTotal";

    public static final String FUNC_GETSTAKINGPERIODS = "getStakingPeriods";

    public static final String FUNC_GETSTAKINGTOKENIDS = "getStakingTokenIds";

    public static final String FUNC_GETWITHDRAWSIGNER = "getWithdrawSigner";

    public static final String FUNC_ORDERISINVALID = "orderIsInvalid";

    public static final String FUNC_SETPAUSE = "setPause";

    public static final String FUNC_SETSTAKINGPERIODS = "setStakingPeriods";

    public static final String FUNC_SETWITHDRAWSIGNER = "setWithdrawSigner";

    public static final String FUNC_STAKING = "staking";

    public static final String FUNC_STAKINGDATAS = "stakingDatas";

    public static final String FUNC_UNSTAKE = "unStake";

    public static final String FUNC_UNSTAKEONLYOWNER = "unStakeOnlyOwner";

    public static final String FUNC_WITHDRAWERC20 = "withdrawERC20";

    public static final String FUNC_YGMETOTAL = "ygmeTotal";

    public static final Event STAKING_EVENT = new Event("Staking", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWERC20_EVENT = new Event("WithdrawERC20", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected YgmeStaking(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected YgmeStaking(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected YgmeStaking(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected YgmeStaking(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public Flowable<StakingEventResponse> stakingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StakingEventResponse>() {
            @Override
            public StakingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STAKING_EVENT, log);
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
        });
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

    public Flowable<WithdrawERC20EventResponse> withdrawERC20EventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawERC20EventResponse>() {
            @Override
            public WithdrawERC20EventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWERC20_EVENT, log);
                WithdrawERC20EventResponse typedResponse = new WithdrawERC20EventResponse();
                typedResponse.log = log;
                typedResponse.orderId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawERC20EventResponse> withdrawERC20EventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWERC20_EVENT));
        return withdrawERC20EventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> ONE_CYCLE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ONE_CYCLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> accountTotal() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ACCOUNTTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getStakingPeriods() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTAKINGPERIODS, 
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
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTAKINGTOKENIDS, 
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
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWITHDRAWSIGNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> orderIsInvalid(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORDERISINVALID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setPause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setStakingPeriods(List<BigInteger> _days) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSTAKINGPERIODS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.StaticArray3<org.web3j.abi.datatypes.generated.Uint64>(
                        org.web3j.abi.datatypes.generated.Uint64.class,
                        org.web3j.abi.Utils.typeMap(_days, org.web3j.abi.datatypes.generated.Uint64.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWithdrawSigner(String _withdrawSigner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWITHDRAWSIGNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _withdrawSigner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> staking(List<BigInteger> _tokenIds, BigInteger _stakeDays) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STAKING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_stakeDays)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, Boolean, BigInteger, BigInteger>> stakingDatas(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STAKINGDATAS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
        return new RemoteFunctionCall<Tuple4<String, Boolean, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, Boolean, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, Boolean, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, Boolean, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> unStake(List<BigInteger> _tokenIds) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unStakeOnlyOwner(List<BigInteger> _tokenIds) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNSTAKEONLYOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawERC20(byte[] data, byte[] signature) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWERC20, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> ygmeTotal() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_YGMETOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static YgmeStaking load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new YgmeStaking(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static YgmeStaking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new YgmeStaking(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static YgmeStaking load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new YgmeStaking(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static YgmeStaking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new YgmeStaking(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<YgmeStaking> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(YgmeStaking.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<YgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(YgmeStaking.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<YgmeStaking> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(YgmeStaking.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<YgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(YgmeStaking.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
