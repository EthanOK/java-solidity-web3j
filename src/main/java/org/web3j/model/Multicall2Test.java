package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class Multicall2Test extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610c62806100326000396000f3fe6080604052600436106100e85760003560e01c80635023a1f21161008a578063a8b0574e11610059578063a8b0574e14610233578063bce38bd714610255578063c3077fa914610282578063ee82ac5e146102a2576100e8565b80635023a1f2146101d457806372425d9d146101f45780638381f58a1461020957806386d516e81461021e576100e8565b8063399542e9116100c6578063399542e91461015b5780633fb5c1cb1461018a57806342cbb15c1461019f5780634d2301cc146101b4576100e8565b80630f28c97d146100ed578063252dba421461011857806327e86d6e14610146575b600080fd5b3480156100f957600080fd5b506101026102c2565b60405161010f9190610a4b565b60405180910390f35b34801561012457600080fd5b506101386101333660046108cb565b6102c6565b60405161010f929190610b45565b34801561015257600080fd5b50610102610405565b34801561016757600080fd5b5061017b610176366004610906565b61040e565b60405161010f93929190610bad565b61019d610198366004610958565b610426565b005b3480156101ab57600080fd5b5061010261042b565b3480156101c057600080fd5b506101026101cf3660046108a9565b61045b565b3480156101e057600080fd5b506101386101ef3660046108cb565b610468565b34801561020057600080fd5b506101026105c2565b34801561021557600080fd5b506101026105c6565b34801561022a57600080fd5b506101026105cc565b34801561023f57600080fd5b506102486105d0565b60405161010f9190610a24565b34801561026157600080fd5b50610275610270366004610906565b6105d4565b60405161010f9190610a38565b34801561028e57600080fd5b5061017b61029d3660046108cb565b61072a565b3480156102ae57600080fd5b506101026102bd366004610958565b610747565b4290565b8051439060609067ffffffffffffffff811180156102e357600080fd5b5060405190808252806020026020018201604052801561031757816020015b60608152602001906001900390816103025790505b50905060005b83518110156103ff576000606085838151811061033657fe5b6020026020010151600001516001600160a01b031686848151811061035757fe5b6020026020010151602001516040516103709190610a08565b6000604051808303816000865af19150503d80600081146103ad576040519150601f19603f3d011682016040523d82523d6000602084013e6103b2565b606091505b5091509150816103dd5760405162461bcd60e51b81526004016103d490610b10565b60405180910390fd5b808484815181106103ea57fe5b6020908102919091010152505060010161031d565b50915091565b60001943014090565b438040606061041d85856105d4565b90509250925092565b600155565b600080546001600160a01b031633146104565760405162461bcd60e51b81526004016103d490610a9a565b504390565b6001600160a01b03163190565b600080546060906001600160a01b031633146104965760405162461bcd60e51b81526004016103d490610a9a565b439150825167ffffffffffffffff811180156104b157600080fd5b506040519080825280602002602001820160405280156104e557816020015b60608152602001906001900390816104d05790505b50905060005b83518110156103ff576000606085838151811061050457fe5b6020026020010151600001516001600160a01b031686848151811061052557fe5b60200260200101516020015160405161053e9190610a08565b600060405180830381855afa9150503d8060008114610579576040519150601f19603f3d011682016040523d82523d6000602084013e61057e565b606091505b5091509150816105a05760405162461bcd60e51b81526004016103d490610a54565b808484815181106105ad57fe5b602090810291909101015250506001016104eb565b4490565b60015481565b4590565b4190565b6060815167ffffffffffffffff811180156105ee57600080fd5b5060405190808252806020026020018201604052801561062857816020015b61061561074b565b81526020019060019003908161060d5790505b50905060005b8251811015610723576000606084838151811061064757fe5b6020026020010151600001516001600160a01b031685848151811061066857fe5b6020026020010151602001516040516106819190610a08565b6000604051808303816000865af19150503d80600081146106be576040519150601f19603f3d011682016040523d82523d6000602084013e6106c3565b606091505b509150915085156106eb57816106eb5760405162461bcd60e51b81526004016103d490610acf565b604051806040016040528083151581526020018281525084848151811061070e57fe5b6020908102919091010152505060010161062e565b5092915050565b600080606061073a60018561040e565b9196909550909350915050565b4090565b60408051808201909152600081526060602082015290565b80356001600160a01b038116811461077a57600080fd5b92915050565b600082601f830112610790578081fd5b813567ffffffffffffffff808211156107a7578283fd5b60206107b68182850201610bd5565b838152935080840185820160005b858110156108345781358801604080601f19838d030112156107e557600080fd5b6107ee81610bd5565b6107fa8c888501610763565b815290820135908782111561080e57600080fd5b61081c8c8884860101610840565b818801528552505091830191908301906001016107c4565b50505050505092915050565b600082601f830112610850578081fd5b813567ffffffffffffffff811115610866578182fd5b610879601f8201601f1916602001610bd5565b915080825283602082850101111561089057600080fd5b8060208401602084013760009082016020015292915050565b6000602082840312156108ba578081fd5b6108c48383610763565b9392505050565b6000602082840312156108dc578081fd5b813567ffffffffffffffff8111156108f2578182fd5b6108fe84828501610780565b949350505050565b60008060408385031215610918578081fd5b82358015158114610927578182fd5b9150602083013567ffffffffffffffff811115610942578182fd5b61094e85828601610780565b9150509250929050565b600060208284031215610969578081fd5b5035919050565b60008282518085526020808601955080818302840101818601855b848110156109cf57858303601f19018952815180511515845284015160408585018190526109bb818601836109dc565b9a86019a945050509083019060010161098b565b5090979650505050505050565b600081518084526109f4816020860160208601610bfc565b601f01601f19169290920160200192915050565b60008251610a1a818460208701610bfc565b9190910192915050565b6001600160a01b0391909116815260200190565b6000602082526108c46020830184610970565b90815260200190565b60208082526026908201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060408201526519985a5b195960d21b606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60208082526021908201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656040820152601960fa1b606082015260800190565b6020808252818101527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c6564604082015260600190565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b82811015610b9f57605f19888703018452610b8d8683516109dc565b95509284019290840190600101610b71565b509398975050505050505050565b600084825283602083015260606040830152610bcc6060830184610970565b95945050505050565b60405181810167ffffffffffffffff81118282101715610bf457600080fd5b604052919050565b60005b83811015610c17578181015183820152602001610bff565b83811115610c26576000848401525b5050505056fea264697066735822122067415cf0ff33367bb1416b71821b150f332f68df62e77c2ffc8713bcb03c5c4364736f6c63430007010033";

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
}
