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
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610c7e806100326000396000f3fe6080604052600436106100e85760003560e01c80635023a1f21161008a578063a8b0574e11610059578063a8b0574e14610233578063bce38bd714610255578063c3077fa914610282578063ee82ac5e146102a2576100e8565b80635023a1f2146101d457806372425d9d146101f45780638381f58a1461020957806386d516e81461021e576100e8565b8063399542e9116100c6578063399542e91461015b5780633fb5c1cb1461018a57806342cbb15c1461019f5780634d2301cc146101b4576100e8565b80630f28c97d146100ed578063252dba421461011857806327e86d6e14610146575b600080fd5b3480156100f957600080fd5b506101026102c2565b60405161010f9190610a6a565b60405180910390f35b34801561012457600080fd5b506101386101333660046108ea565b6102c6565b60405161010f929190610b64565b34801561015257600080fd5b50610102610404565b34801561016757600080fd5b5061017b610176366004610925565b61040d565b60405161010f93929190610bcc565b61019d610198366004610977565b610425565b005b3480156101ab57600080fd5b50610102610465565b3480156101c057600080fd5b506101026101cf3660046108c9565b610495565b3480156101e057600080fd5b506101386101ef3660046108ea565b6104a2565b34801561020057600080fd5b506101026105fb565b34801561021557600080fd5b506101026105ff565b34801561022a57600080fd5b50610102610605565b34801561023f57600080fd5b50610248610609565b60405161010f9190610a43565b34801561026157600080fd5b50610275610270366004610925565b61060d565b60405161010f9190610a57565b34801561028e57600080fd5b5061017b61029d3660046108ea565b610762565b3480156102ae57600080fd5b506101026102bd366004610977565b61077f565b4290565b8051439060609067ffffffffffffffff811180156102e357600080fd5b5060405190808252806020026020018201604052801561031757816020015b60608152602001906001900390816103025790505b50905060005b83518110156103fe5760008085838151811061033557fe5b6020026020010151600001516001600160a01b031686848151811061035657fe5b60200260200101516020015160405161036f9190610a27565b6000604051808303816000865af19150503d80600081146103ac576040519150601f19603f3d011682016040523d82523d6000602084013e6103b1565b606091505b5091509150816103dc5760405162461bcd60e51b81526004016103d390610b2f565b60405180910390fd5b808484815181106103e957fe5b6020908102919091010152505060010161031d565b50915091565b60001943014090565b438040606061041c858561060d565b90509250925092565b60018190556040517f331bb01bcf77ec721a35a558a7984e8e6ca33b507d3ee1dd13b76f64381e54d49061045a908390610a6a565b60405180910390a150565b600080546001600160a01b031633146104905760405162461bcd60e51b81526004016103d390610ab9565b504390565b6001600160a01b03163190565b600080546060906001600160a01b031633146104d05760405162461bcd60e51b81526004016103d390610ab9565b439150825167ffffffffffffffff811180156104eb57600080fd5b5060405190808252806020026020018201604052801561051f57816020015b606081526020019060019003908161050a5790505b50905060005b83518110156103fe5760008085838151811061053d57fe5b6020026020010151600001516001600160a01b031686848151811061055e57fe5b6020026020010151602001516040516105779190610a27565b600060405180830381855afa9150503d80600081146105b2576040519150601f19603f3d011682016040523d82523d6000602084013e6105b7565b606091505b5091509150816105d95760405162461bcd60e51b81526004016103d390610a73565b808484815181106105e657fe5b60209081029190910101525050600101610525565b4490565b60015481565b4590565b4190565b6060815167ffffffffffffffff8111801561062757600080fd5b5060405190808252806020026020018201604052801561066157816020015b61064e610783565b8152602001906001900390816106465790505b50905060005b825181101561075b5760008084838151811061067f57fe5b6020026020010151600001516001600160a01b03168584815181106106a057fe5b6020026020010151602001516040516106b99190610a27565b6000604051808303816000865af19150503d80600081146106f6576040519150601f19603f3d011682016040523d82523d6000602084013e6106fb565b606091505b5091509150851561072357816107235760405162461bcd60e51b81526004016103d390610aee565b604051806040016040528083151581526020018281525084848151811061074657fe5b60209081029190910101525050600101610667565b5092915050565b600080606061077260018561040d565b9196909550909350915050565b4090565b60408051808201909152600081526060602082015290565b80356001600160a01b03811681146107b257600080fd5b919050565b600082601f8301126107c7578081fd5b8135602067ffffffffffffffff808311156107de57fe5b6107eb8283850201610bf4565b83815282810190868401865b868110156108bb57813589016040601f198181848f03011215610818578a8bfd5b81518281018181108a8211171561082b57fe5b8352610838848b0161079b565b8152828401358981111561084a578c8dfd5b8085019450508d603f85011261085e578b8cfd5b898401358981111561086c57fe5b61087c8b84601f84011601610bf4565b92508083528e84828701011115610891578c8dfd5b808486018c85013782018a018c9052808a01919091528652505092850192908501906001016107f7565b509098975050505050505050565b6000602082840312156108da578081fd5b6108e38261079b565b9392505050565b6000602082840312156108fb578081fd5b813567ffffffffffffffff811115610911578182fd5b61091d848285016107b7565b949350505050565b60008060408385031215610937578081fd5b82358015158114610946578182fd5b9150602083013567ffffffffffffffff811115610961578182fd5b61096d858286016107b7565b9150509250929050565b600060208284031215610988578081fd5b5035919050565b60008282518085526020808601955080818302840101818601855b848110156109ee57858303601f19018952815180511515845284015160408585018190526109da818601836109fb565b9a86019a94505050908301906001016109aa565b5090979650505050505050565b60008151808452610a13816020860160208601610c18565b601f01601f19169290920160200192915050565b60008251610a39818460208701610c18565b9190910192915050565b6001600160a01b0391909116815260200190565b6000602082526108e3602083018461098f565b90815260200190565b60208082526026908201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060408201526519985a5b195960d21b606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60208082526021908201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656040820152601960fa1b606082015260800190565b6020808252818101527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c6564604082015260600190565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b82811015610bbe57605f19888703018452610bac8683516109fb565b95509284019290840190600101610b90565b509398975050505050505050565b600084825283602083015260606040830152610beb606083018461098f565b95945050505050565b60405181810167ffffffffffffffff81118282101715610c1057fe5b604052919050565b60005b83811015610c33578181015183820152602001610c1b565b83811115610c42576000848401525b5050505056fea2646970667358221220249e344d4356620a6b051b1d118837102ed315c58bd7d5869e28e8faece1fdc264736f6c63430007060033";

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
