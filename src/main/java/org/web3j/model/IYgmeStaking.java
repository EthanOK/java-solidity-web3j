package org.web3j.model;

import io.reactivex.Flowable;
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
import org.web3j.abi.datatypes.Function;
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
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class IYgmeStaking extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516105d83803806105d883398101604081905261002f91610053565b505050610096565b80516001600160a01b038116811461004e57600080fd5b919050565b60008060006060848603121561006857600080fd5b61007184610037565b925061007f60208501610037565b915061008d60408501610037565b90509250925092565b610533806100a56000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c8063888a8d0a11610097578063d431b1ac11610066578063d431b1ac14610202578063efff2ae5146102bd578063f8c4bbf2146102e0578063fa234a52146102f357600080fd5b8063888a8d0a1461024d5780638c181f001461025f578063b10dcc9314610274578063d11c31e81461029a57600080fd5b806341ad2077116100d357806341ad2077146102045780636232d83f1461022557806376292e531461022c5780637ccbfe7c1461023f57600080fd5b80631738ad861461010557806318e928411461019457806324ca02a3146101bf57806340edabeb146101f1575b600080fd5b610156610113366004610333565b600460205260009081526040902080546001909101546001600160a01b03821691600160a01b900460ff16906001600160801b0380821691600160801b90041684565b604080516001600160a01b03909516855292151560208501526001600160801b03918216928401929092521660608201526080015b60405180910390f35b6002546101a7906001600160a01b031681565b6040516001600160a01b03909116815260200161018b565b6007546101d990600160801b90046001600160801b031681565b6040516001600160801b03909116815260200161018b565b6102026101ff36600461034c565b50565b005b610218610212366004610374565b50606090565b60405161018b91906103a4565b60006101a7565b6001546101a7906001600160a01b031681565b6102026101ff366004610374565b61020261025b366004610434565b5050565b61026761030a565b60405161018b9190610476565b61028a610282366004610434565b600192915050565b604051901515815260200161018b565b6102a46201518081565b60405167ffffffffffffffff909116815260200161018b565b61028a6102cb366004610333565b60066020526000908152604090205460ff1681565b6007546101d9906001600160801b031681565b61028a6103013660046104b1565b60019392505050565b610312610315565b90565b60405180606001604052806003906020820280368337509192915050565b60006020828403121561034557600080fd5b5035919050565b60006060828403121561035e57600080fd5b8260608301111561036e57600080fd5b50919050565b60006020828403121561038657600080fd5b81356001600160a01b038116811461039d57600080fd5b9392505050565b6020808252825182820181905260009190848201906040850190845b818110156103dc578351835292840192918401916001016103c0565b50909695505050505050565b60008083601f8401126103fa57600080fd5b50813567ffffffffffffffff81111561041257600080fd5b6020830191508360208260051b850101111561042d57600080fd5b9250929050565b6000806020838503121561044757600080fd5b823567ffffffffffffffff81111561045e57600080fd5b61046a858286016103e8565b90969095509350505050565b60608101818360005b60038110156104a857815167ffffffffffffffff1683526020928301929091019060010161047f565b50505092915050565b6000806000604084860312156104c657600080fd5b833567ffffffffffffffff8111156104dd57600080fd5b6104e9868287016103e8565b90979096506020959095013594935050505056fea264697066735822122009b79caae0027fedfb232c1daf5e673764030219788c5de514e2759bb45673fb64736f6c63430008110033";

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

    public static final String FUNC_YGIO = "ygio";

    public static final String FUNC_YGME = "ygme";

    public static final String FUNC_YGMETOTAL = "ygmeTotal";

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

    public RemoteFunctionCall<BigInteger> ONE_CYCLE() {
        final Function function = new Function(FUNC_ONE_CYCLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> accountTotal() {
        final Function function = new Function(FUNC_ACCOUNTTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<Boolean> orderIsInvalid(BigInteger param0) {
        final Function function = new Function(FUNC_ORDERISINVALID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setPause() {
        final Function function = new Function(
                FUNC_SETPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setStakingPeriods(List<BigInteger> _days) {
        final Function function = new Function(
                FUNC_SETSTAKINGPERIODS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.StaticArray3<org.web3j.abi.datatypes.generated.Uint64>(
                        org.web3j.abi.datatypes.generated.Uint64.class,
                        org.web3j.abi.Utils.typeMap(_days, org.web3j.abi.datatypes.generated.Uint64.class))), 
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

    public RemoteFunctionCall<TransactionReceipt> staking(List<BigInteger> _tokenIds, BigInteger _stakeDays) {
        final Function function = new Function(
                FUNC_STAKING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_stakeDays)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, Boolean, BigInteger, BigInteger>> stakingDatas(BigInteger param0) {
        final Function function = new Function(FUNC_STAKINGDATAS, 
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
        final Function function = new Function(
                FUNC_UNSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unStakeOnlyOwner(List<BigInteger> _tokenIds) {
        final Function function = new Function(
                FUNC_UNSTAKEONLYOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_tokenIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> ygio() {
        final Function function = new Function(FUNC_YGIO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ygme() {
        final Function function = new Function(FUNC_YGME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> ygmeTotal() {
        final Function function = new Function(FUNC_YGMETOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(IYgmeStaking.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(IYgmeStaking.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(IYgmeStaking.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IYgmeStaking> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _ygme, String _ygio, String _withdrawSigner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _ygme), 
                new org.web3j.abi.datatypes.Address(160, _ygio), 
                new org.web3j.abi.datatypes.Address(160, _withdrawSigner)));
        return deployRemoteCall(IYgmeStaking.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
