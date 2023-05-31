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
    public static final String BINARY = "608060405234801561001057600080fd5b50610abf806100206000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c80635023a1f21161008c578063a8b0574e11610066578063a8b0574e1461017b578063bce38bd714610190578063c3077fa9146101b0578063ee82ac5e146101c3576100cf565b80635023a1f21461015857806372425d9d1461016b57806386d516e814610173576100cf565b80630f28c97d146100d4578063252dba42146100f257806327e86d6e14610113578063399542e91461011b57806342cbb15c1461013d5780634d2301cc14610145575b600080fd5b6100dc6101d6565b6040516100e991906108e0565b60405180910390f35b610105610100366004610760565b6101da565b6040516100e99291906109a5565b6100dc610318565b61012e61012936600461079b565b610321565b6040516100e993929190610a0d565b6100dc610339565b6100dc61015336600461073f565b61033d565b610105610166366004610760565b61034a565b6100dc610477565b6100dc61047b565b61018361047f565b6040516100e991906108b9565b6101a361019e36600461079b565b610483565b6040516100e991906108cd565b61012e6101be366004610760565b6105d8565b6100dc6101d13660046107ed565b6105f5565b4290565b8051439060609067ffffffffffffffff811180156101f757600080fd5b5060405190808252806020026020018201604052801561022b57816020015b60608152602001906001900390816102165790505b50905060005b83518110156103125760008085838151811061024957fe5b6020026020010151600001516001600160a01b031686848151811061026a57fe5b602002602001015160200151604051610283919061089d565b6000604051808303816000865af19150503d80600081146102c0576040519150601f19603f3d011682016040523d82523d6000602084013e6102c5565b606091505b5091509150816102f05760405162461bcd60e51b81526004016102e790610970565b60405180910390fd5b808484815181106102fd57fe5b60209081029190910101525050600101610231565b50915091565b60001943014090565b43804060606103308585610483565b90509250925092565b4390565b6001600160a01b03163190565b8051439060609067ffffffffffffffff8111801561036757600080fd5b5060405190808252806020026020018201604052801561039b57816020015b60608152602001906001900390816103865790505b50905060005b8351811015610312576000808583815181106103b957fe5b6020026020010151600001516001600160a01b03168684815181106103da57fe5b6020026020010151602001516040516103f3919061089d565b600060405180830381855afa9150503d806000811461042e576040519150601f19603f3d011682016040523d82523d6000602084013e610433565b606091505b5091509150816104555760405162461bcd60e51b81526004016102e7906108e9565b8084848151811061046257fe5b602090810291909101015250506001016103a1565b4490565b4590565b4190565b6060815167ffffffffffffffff8111801561049d57600080fd5b506040519080825280602002602001820160405280156104d757816020015b6104c46105f9565b8152602001906001900390816104bc5790505b50905060005b82518110156105d1576000808483815181106104f557fe5b6020026020010151600001516001600160a01b031685848151811061051657fe5b60200260200101516020015160405161052f919061089d565b6000604051808303816000865af19150503d806000811461056c576040519150601f19603f3d011682016040523d82523d6000602084013e610571565b606091505b5091509150851561059957816105995760405162461bcd60e51b81526004016102e79061092f565b60405180604001604052808315158152602001828152508484815181106105bc57fe5b602090810291909101015250506001016104dd565b5092915050565b60008060606105e8600185610321565b9196909550909350915050565b4090565b60408051808201909152600081526060602082015290565b80356001600160a01b038116811461062857600080fd5b919050565b600082601f83011261063d578081fd5b8135602067ffffffffffffffff8083111561065457fe5b6106618283850201610a35565b83815282810190868401865b8681101561073157813589016040601f198181848f0301121561068e578a8bfd5b81518281018181108a821117156106a157fe5b83526106ae848b01610611565b815282840135898111156106c0578c8dfd5b8085019450508d603f8501126106d4578b8cfd5b89840135898111156106e257fe5b6106f28b84601f84011601610a35565b92508083528e84828701011115610707578c8dfd5b808486018c85013782018a018c9052808a019190915286525050928501929085019060010161066d565b509098975050505050505050565b600060208284031215610750578081fd5b61075982610611565b9392505050565b600060208284031215610771578081fd5b813567ffffffffffffffff811115610787578182fd5b6107938482850161062d565b949350505050565b600080604083850312156107ad578081fd5b823580151581146107bc578182fd5b9150602083013567ffffffffffffffff8111156107d7578182fd5b6107e38582860161062d565b9150509250929050565b6000602082840312156107fe578081fd5b5035919050565b60008282518085526020808601955080818302840101818601855b8481101561086457858303601f190189528151805115158452840151604085850181905261085081860183610871565b9a86019a9450505090830190600101610820565b5090979650505050505050565b60008151808452610889816020860160208601610a59565b601f01601f19169290920160200192915050565b600082516108af818460208701610a59565b9190910192915050565b6001600160a01b0391909116815260200190565b6000602082526107596020830184610805565b90815260200190565b60208082526026908201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060408201526519985a5b195960d21b606082015260800190565b60208082526021908201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656040820152601960fa1b606082015260800190565b6020808252818101527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c6564604082015260600190565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b828110156109ff57605f198887030184526109ed868351610871565b955092840192908401906001016109d1565b509398975050505050505050565b600084825283602083015260606040830152610a2c6060830184610805565b95945050505050565b60405181810167ffffffffffffffff81118282101715610a5157fe5b604052919050565b60005b83811015610a74578181015183820152602001610a5c565b83811115610a83576000848401525b5050505056fea264697066735822122044d971064b30eb4af9fb81a7d9aac83d9ecb7ea700cc97fd7b0cd6f73300fccd64736f6c63430007060033";

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
