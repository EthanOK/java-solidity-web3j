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
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610bb9806100326000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c80635023a1f211610097578063a8b0574e11610066578063a8b0574e146101be578063bce38bd7146101d3578063c3077fa9146101f3578063ee82ac5e14610206576100f5565b80635023a1f21461019357806372425d9d146101a65780638381f58a146101ae57806386d516e8146101b6576100f5565b8063399542e9116100d3578063399542e9146101415780633fb5c1cb1461016357806342cbb15c146101785780634d2301cc14610180576100f5565b80630f28c97d146100fa578063252dba421461011857806327e86d6e14610139575b600080fd5b610102610219565b60405161010f91906109a2565b60405180910390f35b61012b610126366004610822565b61021d565b60405161010f929190610a9c565b61010261035c565b61015461014f36600461085d565b610365565b60405161010f93929190610b04565b6101766101713660046108af565b61037d565b005b610102610382565b61010261018e366004610800565b6103b2565b61012b6101a1366004610822565b6103bf565b610102610519565b61010261051d565b610102610523565b6101c6610527565b60405161010f919061097b565b6101e66101e136600461085d565b61052b565b60405161010f919061098f565b610154610201366004610822565b610681565b6101026102143660046108af565b61069e565b4290565b8051439060609067ffffffffffffffff8111801561023a57600080fd5b5060405190808252806020026020018201604052801561026e57816020015b60608152602001906001900390816102595790505b50905060005b8351811015610356576000606085838151811061028d57fe5b6020026020010151600001516001600160a01b03168684815181106102ae57fe5b6020026020010151602001516040516102c7919061095f565b6000604051808303816000865af19150503d8060008114610304576040519150601f19603f3d011682016040523d82523d6000602084013e610309565b606091505b5091509150816103345760405162461bcd60e51b815260040161032b90610a67565b60405180910390fd5b8084848151811061034157fe5b60209081029190910101525050600101610274565b50915091565b60001943014090565b4380406060610374858561052b565b90509250925092565b600155565b600080546001600160a01b031633146103ad5760405162461bcd60e51b815260040161032b906109f1565b504390565b6001600160a01b03163190565b600080546060906001600160a01b031633146103ed5760405162461bcd60e51b815260040161032b906109f1565b439150825167ffffffffffffffff8111801561040857600080fd5b5060405190808252806020026020018201604052801561043c57816020015b60608152602001906001900390816104275790505b50905060005b8351811015610356576000606085838151811061045b57fe5b6020026020010151600001516001600160a01b031686848151811061047c57fe5b602002602001015160200151604051610495919061095f565b600060405180830381855afa9150503d80600081146104d0576040519150601f19603f3d011682016040523d82523d6000602084013e6104d5565b606091505b5091509150816104f75760405162461bcd60e51b815260040161032b906109ab565b8084848151811061050457fe5b60209081029190910101525050600101610442565b4490565b60015481565b4590565b4190565b6060815167ffffffffffffffff8111801561054557600080fd5b5060405190808252806020026020018201604052801561057f57816020015b61056c6106a2565b8152602001906001900390816105645790505b50905060005b825181101561067a576000606084838151811061059e57fe5b6020026020010151600001516001600160a01b03168584815181106105bf57fe5b6020026020010151602001516040516105d8919061095f565b6000604051808303816000865af19150503d8060008114610615576040519150601f19603f3d011682016040523d82523d6000602084013e61061a565b606091505b5091509150851561064257816106425760405162461bcd60e51b815260040161032b90610a26565b604051806040016040528083151581526020018281525084848151811061066557fe5b60209081029190910101525050600101610585565b5092915050565b6000806060610691600185610365565b9196909550909350915050565b4090565b60408051808201909152600081526060602082015290565b80356001600160a01b03811681146106d157600080fd5b92915050565b600082601f8301126106e7578081fd5b813567ffffffffffffffff808211156106fe578283fd5b602061070d8182850201610b2c565b838152935080840185820160005b8581101561078b5781358801604080601f19838d0301121561073c57600080fd5b61074581610b2c565b6107518c8885016106ba565b815290820135908782111561076557600080fd5b6107738c8884860101610797565b8188015285525050918301919083019060010161071b565b50505050505092915050565b600082601f8301126107a7578081fd5b813567ffffffffffffffff8111156107bd578182fd5b6107d0601f8201601f1916602001610b2c565b91508082528360208285010111156107e757600080fd5b8060208401602084013760009082016020015292915050565b600060208284031215610811578081fd5b61081b83836106ba565b9392505050565b600060208284031215610833578081fd5b813567ffffffffffffffff811115610849578182fd5b610855848285016106d7565b949350505050565b6000806040838503121561086f578081fd5b8235801515811461087e578182fd5b9150602083013567ffffffffffffffff811115610899578182fd5b6108a5858286016106d7565b9150509250929050565b6000602082840312156108c0578081fd5b5035919050565b60008282518085526020808601955080818302840101818601855b8481101561092657858303601f190189528151805115158452840151604085850181905261091281860183610933565b9a86019a94505050908301906001016108e2565b5090979650505050505050565b6000815180845261094b816020860160208601610b53565b601f01601f19169290920160200192915050565b60008251610971818460208701610b53565b9190910192915050565b6001600160a01b0391909116815260200190565b60006020825261081b60208301846108c7565b90815260200190565b60208082526026908201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060408201526519985a5b195960d21b606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60208082526021908201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656040820152601960fa1b606082015260800190565b6020808252818101527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c6564604082015260600190565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b82811015610af657605f19888703018452610ae4868351610933565b95509284019290840190600101610ac8565b509398975050505050505050565b600084825283602083015260606040830152610b2360608301846108c7565b95945050505050565b60405181810167ffffffffffffffff81118282101715610b4b57600080fd5b604052919050565b60005b83811015610b6e578181015183820152602001610b56565b83811115610b7d576000848401525b5050505056fea264697066735822122081514bb5606c9a10bb3a2290eb3b97367d6e29193deead0697b9a5ad72e2748664736f6c63430007010033";

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

    public RemoteFunctionCall<TransactionReceipt> setNumber(BigInteger number_) {
        final Function function = new Function(
                FUNC_SETNUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(number_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
