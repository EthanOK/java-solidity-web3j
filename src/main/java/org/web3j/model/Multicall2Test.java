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
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class Multicall2Test extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610d65806100326000396000f3fe6080604052600436106100e85760003560e01c80635023a1f21161008a578063a8b0574e11610059578063a8b0574e1461022f578063bce38bd71461024a578063c3077fa914610277578063ee82ac5e1461029757600080fd5b80635023a1f2146101d357806372425d9d146101f35780638381f58a1461020657806386d516e81461021c57600080fd5b8063399542e9116100c6578063399542e9146101525780633fb5c1cb1461018157806342cbb15c146101965780634d2301cc146101ab57600080fd5b80630f28c97d146100ed578063252dba421461010f57806327e86d6e1461013d575b600080fd5b3480156100f957600080fd5b50425b6040519081526020015b60405180910390f35b34801561011b57600080fd5b5061012f61012a366004610a88565b6102b6565b604051610106929190610b15565b34801561014957600080fd5b506100fc61043e565b34801561015e57600080fd5b5061017261016d366004610b7f565b610451565b60405161010693929190610c3f565b61019461018f366004610c67565b610469565b005b3480156101a257600080fd5b506100fc6104a4565b3480156101b757600080fd5b506100fc6101c6366004610c80565b6001600160a01b03163190565b3480156101df57600080fd5b5061012f6101ee366004610a88565b610504565b3480156101ff57600080fd5b50446100fc565b34801561021257600080fd5b506100fc60015481565b34801561022857600080fd5b50456100fc565b34801561023b57600080fd5b50604051418152602001610106565b34801561025657600080fd5b5061026a610265366004610b7f565b6106ea565b6040516101069190610ca2565b34801561028357600080fd5b50610172610292366004610a88565b6108a4565b3480156102a357600080fd5b506100fc6102b2366004610c67565b4090565b8051439060609067ffffffffffffffff8111156102d5576102d56108c1565b60405190808252806020026020018201604052801561030857816020015b60608152602001906001900390816102f35790505b50905060005b83518110156104385760008085838151811061032c5761032c610cb5565b6020026020010151600001516001600160a01b031686848151811061035357610353610cb5565b60200260200101516020015160405161036c9190610ccb565b6000604051808303816000865af19150503d80600081146103a9576040519150601f19603f3d011682016040523d82523d6000602084013e6103ae565b606091505b5091509150816104055760405162461bcd60e51b815260206004820181905260248201527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c656460448201526064015b60405180910390fd5b8084848151811061041857610418610cb5565b60200260200101819052505050808061043090610cfd565b91505061030e565b50915091565b600061044b600143610d16565b40905090565b438040606061046085856106ea565b90509250925092565b60018190556040518181527f331bb01bcf77ec721a35a558a7984e8e6ca33b507d3ee1dd13b76f64381e54d49060200160405180910390a150565b600080546001600160a01b031633146104ff5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016103fc565b504390565b600080546060906001600160a01b031633146105625760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016103fc565b439150825167ffffffffffffffff81111561057f5761057f6108c1565b6040519080825280602002602001820160405280156105b257816020015b606081526020019060019003908161059d5790505b50905060005b8351811015610438576000808583815181106105d6576105d6610cb5565b6020026020010151600001516001600160a01b03168684815181106105fd576105fd610cb5565b6020026020010151602001516040516106169190610ccb565b600060405180830381855afa9150503d8060008114610651576040519150601f19603f3d011682016040523d82523d6000602084013e610656565b606091505b5091509150816106b75760405162461bcd60e51b815260206004820152602660248201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060448201526519985a5b195960d21b60648201526084016103fc565b808484815181106106ca576106ca610cb5565b6020026020010181905250505080806106e290610cfd565b9150506105b8565b6060815167ffffffffffffffff811115610706576107066108c1565b60405190808252806020026020018201604052801561074c57816020015b6040805180820190915260008152606060208201528152602001906001900390816107245790505b50905060005b825181101561089d5760008084838151811061077057610770610cb5565b6020026020010151600001516001600160a01b031685848151811061079757610797610cb5565b6020026020010151602001516040516107b09190610ccb565b6000604051808303816000865af19150503d80600081146107ed576040519150601f19603f3d011682016040523d82523d6000602084013e6107f2565b606091505b5091509150851561085457816108545760405162461bcd60e51b815260206004820152602160248201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656044820152601960fa1b60648201526084016103fc565b604051806040016040528083151581526020018281525084848151811061087d5761087d610cb5565b60200260200101819052505050808061089590610cfd565b915050610752565b5092915050565b60008060606108b4600185610451565b9196909550909350915050565b634e487b7160e01b600052604160045260246000fd5b6040805190810167ffffffffffffffff811182821017156108fa576108fa6108c1565b60405290565b604051601f8201601f1916810167ffffffffffffffff81118282101715610929576109296108c1565b604052919050565b80356001600160a01b038116811461094857600080fd5b919050565b6000601f838184011261095f57600080fd5b8235602067ffffffffffffffff8083111561097c5761097c6108c1565b8260051b61098b838201610900565b93845286810183019383810190898611156109a557600080fd5b84890192505b85831015610a7b578235848111156109c35760008081fd5b89016040601f19828d0381018213156109dc5760008081fd5b6109e46108d7565b6109ef898501610931565b81528284013588811115610a035760008081fd5b8085019450508d603f850112610a195760008081fd5b8884013588811115610a2d57610a2d6108c1565b610a3c8a848e84011601610900565b92508083528e84828701011115610a535760008081fd5b808486018b85013760009083018a0152808901919091528452505091840191908401906109ab565b9998505050505050505050565b600060208284031215610a9a57600080fd5b813567ffffffffffffffff811115610ab157600080fd5b610abd8482850161094d565b949350505050565b60005b83811015610ae0578181015183820152602001610ac8565b50506000910152565b60008151808452610b01816020860160208601610ac5565b601f01601f19169290920160200192915050565b600060408201848352602060408185015281855180845260608601915060608160051b870101935082870160005b82811015610b7157605f19888703018452610b5f868351610ae9565b95509284019290840190600101610b43565b509398975050505050505050565b60008060408385031215610b9257600080fd5b82358015158114610ba257600080fd5b9150602083013567ffffffffffffffff811115610bbe57600080fd5b610bca8582860161094d565b9150509250929050565b600081518084526020808501808196508360051b8101915082860160005b85811015610c3257828403895281518051151585528501516040868601819052610c1e81870183610ae9565b9a87019a9550505090840190600101610bf2565b5091979650505050505050565b838152826020820152606060408201526000610c5e6060830184610bd4565b95945050505050565b600060208284031215610c7957600080fd5b5035919050565b600060208284031215610c9257600080fd5b610c9b82610931565b9392505050565b602081526000610c9b6020830184610bd4565b634e487b7160e01b600052603260045260246000fd5b60008251610cdd818460208701610ac5565b9190910192915050565b634e487b7160e01b600052601160045260246000fd5b600060018201610d0f57610d0f610ce7565b5060010190565b81810381811115610d2957610d29610ce7565b9291505056fea26469706673582212206589364cc14beb746528bedc9a89f820189479983c470754efaadeabe6a980ea64736f6c63430008110033";

    public static final String FUNC_AGGREGATE = "aggregate";

    public static final String FUNC_AGGREGATESTATICCALL = "aggregateStaticCall";

    public static final String FUNC_BLOCKANDAGGREGATE = "blockAndAggregate";

    public static final String FUNC_GETBLOCKHASH = "getBlockHash";

    public static final String FUNC_GETBLOCKNUMBER = "getBlockNumber";

    public static final String FUNC_GETCURRENTBLOCKCOINBASE = "getCurrentBlockCoinbase";

    public static final String FUNC_GETCURRENTBLOCKDIFFICULTY = "getCurrentBlockDifficulty";

    public static final String FUNC_GETCURRENTBLOCKGASLIMIT = "getCurrentBlockGasLimit";

    public static final String FUNC_GETCURRENTBLOCKTIMESTAMP = "getCurrentBlockTimestamp";

    public static final String FUNC_GETETHBALANCE = "getEthBalance";

    public static final String FUNC_GETLASTBLOCKHASH = "getLastBlockHash";

    public static final String FUNC_NUMBER = "number";

    public static final String FUNC_SETNUMBER = "setNumber";

    public static final String FUNC_TRYAGGREGATE = "tryAggregate";

    public static final String FUNC_TRYBLOCKANDAGGREGATE = "tryBlockAndAggregate";

    public static final Event SETNUMBER_EVENT = new Event("SetNumber", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Multicall2Test(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Multicall2Test(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Multicall2Test(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Multicall2Test(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<SetNumberEventResponse> getSetNumberEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SETNUMBER_EVENT, transactionReceipt);
        ArrayList<SetNumberEventResponse> responses = new ArrayList<SetNumberEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetNumberEventResponse typedResponse = new SetNumberEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.number_ = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SetNumberEventResponse getSetNumberEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SETNUMBER_EVENT, log);
        SetNumberEventResponse typedResponse = new SetNumberEventResponse();
        typedResponse.log = log;
        typedResponse.number_ = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<SetNumberEventResponse> setNumberEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSetNumberEventFromLog(log));
    }

    public Flowable<SetNumberEventResponse> setNumberEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETNUMBER_EVENT));
        return setNumberEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> aggregate(List<Call> calls) {
        final Function function = new Function(
                FUNC_AGGREGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> blockAndAggregate(List<Call> calls) {
        final Function function = new Function(
                FUNC_BLOCKANDAGGREGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> getBlockHash(BigInteger blockNumber) {
        final Function function = new Function(FUNC_GETBLOCKHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> getBlockNumber() {
        final Function function = new Function(FUNC_GETBLOCKNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getCurrentBlockCoinbase() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKCOINBASE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getCurrentBlockDifficulty() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKDIFFICULTY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCurrentBlockGasLimit() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKGASLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCurrentBlockTimestamp() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKTIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getEthBalance(String addr) {
        final Function function = new Function(FUNC_GETETHBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> getLastBlockHash() {
        final Function function = new Function(FUNC_GETLASTBLOCKHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> number() {
        final Function function = new Function(FUNC_NUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setNumber(BigInteger number_, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETNUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(number_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> tryAggregate(Boolean requireSuccess, List<Call> calls) {
        final Function function = new Function(
                FUNC_TRYAGGREGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(requireSuccess), 
                new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> tryBlockAndAggregate(Boolean requireSuccess, List<Call> calls) {
        final Function function = new Function(
                FUNC_TRYBLOCKANDAGGREGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(requireSuccess), 
                new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Multicall2Test load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall2Test(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Multicall2Test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall2Test(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Multicall2Test load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Multicall2Test(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Multicall2Test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Multicall2Test(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Multicall2Test> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall2Test.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Multicall2Test> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall2Test.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall2Test> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall2Test.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall2Test> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall2Test.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public static class Result extends DynamicStruct {
        public Boolean success;

        public byte[] returnData;

        public Result(Boolean success, byte[] returnData) {
            super(new org.web3j.abi.datatypes.Bool(success), 
                    new org.web3j.abi.datatypes.DynamicBytes(returnData));
            this.success = success;
            this.returnData = returnData;
        }

        public Result(Bool success, DynamicBytes returnData) {
            super(success, returnData);
            this.success = success.getValue();
            this.returnData = returnData.getValue();
        }
    }

    public static class SetNumberEventResponse extends BaseEventResponse {
        public BigInteger number_;
    }
}
