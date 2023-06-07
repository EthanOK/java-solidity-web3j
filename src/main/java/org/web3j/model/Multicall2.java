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
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class Multicall2 extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b7c806100206000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c80635023a1f21161008c578063a8b0574e11610066578063a8b0574e14610174578063bce38bd714610182578063c3077fa9146101a2578063ee82ac5e146101b557600080fd5b80635023a1f21461015557806372425d9d1461016857806386d516e81461016e57600080fd5b80630f28c97d146100d4578063252dba42146100e957806327e86d6e1461010a578063399542e91461011257806342cbb15c146101345780634d2301cc1461013a575b600080fd5b425b6040519081526020015b60405180910390f35b6100fc6100f73660046108a2565b6101c7565b6040516100e092919061092f565b6100d661034f565b610125610120366004610999565b610362565b6040516100e093929190610a56565b436100d6565b6100d6610148366004610a7e565b6001600160a01b03163190565b6100fc6101633660046108a2565b61037a565b446100d6565b456100d6565b6040514181526020016100e0565b610195610190366004610999565b610504565b6040516100e09190610aa0565b6101256101b03660046108a2565b6106be565b6100d66101c3366004610ab3565b4090565b8051439060609067ffffffffffffffff8111156101e6576101e66106db565b60405190808252806020026020018201604052801561021957816020015b60608152602001906001900390816102045790505b50905060005b83518110156103495760008085838151811061023d5761023d610acc565b6020026020010151600001516001600160a01b031686848151811061026457610264610acc565b60200260200101516020015160405161027d9190610ae2565b6000604051808303816000865af19150503d80600081146102ba576040519150601f19603f3d011682016040523d82523d6000602084013e6102bf565b606091505b5091509150816103165760405162461bcd60e51b815260206004820181905260248201527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c656460448201526064015b60405180910390fd5b8084848151811061032957610329610acc565b60200260200101819052505050808061034190610b14565b91505061021f565b50915091565b600061035c600143610b2d565b40905090565b43804060606103718585610504565b90509250925092565b8051439060609067ffffffffffffffff811115610399576103996106db565b6040519080825280602002602001820160405280156103cc57816020015b60608152602001906001900390816103b75790505b50905060005b8351811015610349576000808583815181106103f0576103f0610acc565b6020026020010151600001516001600160a01b031686848151811061041757610417610acc565b6020026020010151602001516040516104309190610ae2565b600060405180830381855afa9150503d806000811461046b576040519150601f19603f3d011682016040523d82523d6000602084013e610470565b606091505b5091509150816104d15760405162461bcd60e51b815260206004820152602660248201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060448201526519985a5b195960d21b606482015260840161030d565b808484815181106104e4576104e4610acc565b6020026020010181905250505080806104fc90610b14565b9150506103d2565b6060815167ffffffffffffffff811115610520576105206106db565b60405190808252806020026020018201604052801561056657816020015b60408051808201909152600081526060602082015281526020019060019003908161053e5790505b50905060005b82518110156106b75760008084838151811061058a5761058a610acc565b6020026020010151600001516001600160a01b03168584815181106105b1576105b1610acc565b6020026020010151602001516040516105ca9190610ae2565b6000604051808303816000865af19150503d8060008114610607576040519150601f19603f3d011682016040523d82523d6000602084013e61060c565b606091505b5091509150851561066e578161066e5760405162461bcd60e51b815260206004820152602160248201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656044820152601960fa1b606482015260840161030d565b604051806040016040528083151581526020018281525084848151811061069757610697610acc565b6020026020010181905250505080806106af90610b14565b91505061056c565b5092915050565b60008060606106ce600185610362565b9196909550909350915050565b634e487b7160e01b600052604160045260246000fd5b6040805190810167ffffffffffffffff81118282101715610714576107146106db565b60405290565b604051601f8201601f1916810167ffffffffffffffff81118282101715610743576107436106db565b604052919050565b80356001600160a01b038116811461076257600080fd5b919050565b6000601f838184011261077957600080fd5b8235602067ffffffffffffffff80831115610796576107966106db565b8260051b6107a583820161071a565b93845286810183019383810190898611156107bf57600080fd5b84890192505b85831015610895578235848111156107dd5760008081fd5b89016040601f19828d0381018213156107f65760008081fd5b6107fe6106f1565b61080989850161074b565b8152828401358881111561081d5760008081fd5b8085019450508d603f8501126108335760008081fd5b8884013588811115610847576108476106db565b6108568a848e8401160161071a565b92508083528e8482870101111561086d5760008081fd5b808486018b85013760009083018a0152808901919091528452505091840191908401906107c5565b9998505050505050505050565b6000602082840312156108b457600080fd5b813567ffffffffffffffff8111156108cb57600080fd5b6108d784828501610767565b949350505050565b60005b838110156108fa5781810151838201526020016108e2565b50506000910152565b6000815180845261091b8160208601602086016108df565b601f01601f19169290920160200192915050565b600060408201848352602060408185015281855180845260608601915060608160051b870101935082870160005b8281101561098b57605f19888703018452610979868351610903565b9550928401929084019060010161095d565b509398975050505050505050565b600080604083850312156109ac57600080fd5b823580151581146109bc57600080fd5b9150602083013567ffffffffffffffff8111156109d857600080fd5b6109e485828601610767565b9150509250929050565b6000815180845260208085019450848260051b860182860160005b85811015610a4957838303895281518051151584528501516040868501819052610a3581860183610903565b9a87019a9450505090840190600101610a09565b5090979650505050505050565b838152826020820152606060408201526000610a7560608301846109ee565b95945050505050565b600060208284031215610a9057600080fd5b610a998261074b565b9392505050565b602081526000610a9960208301846109ee565b600060208284031215610ac557600080fd5b5035919050565b634e487b7160e01b600052603260045260246000fd5b60008251610af48184602087016108df565b9190910192915050565b634e487b7160e01b600052601160045260246000fd5b600060018201610b2657610b26610afe565b5060010190565b81810381811115610b4057610b40610afe565b9291505056fea2646970667358221220d3d58c94c55ac03689a97ff33b8fba8cd0d9cb765342de2d63bd0f594943df1464736f6c63430008110033";

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

    public static final String FUNC_TRYAGGREGATE = "tryAggregate";

    public static final String FUNC_TRYBLOCKANDAGGREGATE = "tryBlockAndAggregate";

    @Deprecated
    protected Multicall2(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Multicall2(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Multicall2(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Multicall2(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
    public static Multicall2 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Multicall2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Multicall2 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Multicall2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Multicall2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Multicall2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Multicall2> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall2.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall2> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall2.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Multicall2> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall2.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall2> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall2.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
