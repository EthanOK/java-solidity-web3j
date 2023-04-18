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
public class Multicall2 extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ade806100206000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c80635023a1f21161008c578063a8b0574e11610066578063a8b0574e1461017b578063bce38bd714610190578063c3077fa9146101b0578063ee82ac5e146101c3576100cf565b80635023a1f21461015857806372425d9d1461016b57806386d516e814610173576100cf565b80630f28c97d146100d4578063252dba42146100f257806327e86d6e14610113578063399542e91461011b57806342cbb15c1461013d5780634d2301cc14610145575b600080fd5b6100dc6101d6565b6040516100e991906108fc565b60405180910390f35b61010561010036600461077c565b6101da565b6040516100e99291906109c1565b6100dc610319565b61012e6101293660046107b7565b610322565b6040516100e993929190610a29565b6100dc61033a565b6100dc61015336600461075a565b61033e565b61010561016636600461077c565b61034b565b6100dc610479565b6100dc61047d565b610183610481565b6040516100e991906108d5565b6101a361019e3660046107b7565b610485565b6040516100e991906108e9565b61012e6101be36600461077c565b6105db565b6100dc6101d1366004610809565b6105f8565b4290565b8051439060609067ffffffffffffffff811180156101f757600080fd5b5060405190808252806020026020018201604052801561022b57816020015b60608152602001906001900390816102165790505b50905060005b8351811015610313576000606085838151811061024a57fe5b6020026020010151600001516001600160a01b031686848151811061026b57fe5b60200260200101516020015160405161028491906108b9565b6000604051808303816000865af19150503d80600081146102c1576040519150601f19603f3d011682016040523d82523d6000602084013e6102c6565b606091505b5091509150816102f15760405162461bcd60e51b81526004016102e89061098c565b60405180910390fd5b808484815181106102fe57fe5b60209081029190910101525050600101610231565b50915091565b60001943014090565b43804060606103318585610485565b90509250925092565b4390565b6001600160a01b03163190565b8051439060609067ffffffffffffffff8111801561036857600080fd5b5060405190808252806020026020018201604052801561039c57816020015b60608152602001906001900390816103875790505b50905060005b835181101561031357600060608583815181106103bb57fe5b6020026020010151600001516001600160a01b03168684815181106103dc57fe5b6020026020010151602001516040516103f591906108b9565b600060405180830381855afa9150503d8060008114610430576040519150601f19603f3d011682016040523d82523d6000602084013e610435565b606091505b5091509150816104575760405162461bcd60e51b81526004016102e890610905565b8084848151811061046457fe5b602090810291909101015250506001016103a2565b4490565b4590565b4190565b6060815167ffffffffffffffff8111801561049f57600080fd5b506040519080825280602002602001820160405280156104d957816020015b6104c66105fc565b8152602001906001900390816104be5790505b50905060005b82518110156105d457600060608483815181106104f857fe5b6020026020010151600001516001600160a01b031685848151811061051957fe5b60200260200101516020015160405161053291906108b9565b6000604051808303816000865af19150503d806000811461056f576040519150601f19603f3d011682016040523d82523d6000602084013e610574565b606091505b5091509150851561059c578161059c5760405162461bcd60e51b81526004016102e89061094b565b60405180604001604052808315158152602001828152508484815181106105bf57fe5b602090810291909101015250506001016104df565b5092915050565b60008060606105eb600185610322565b9196909550909350915050565b4090565b60408051808201909152600081526060602082015290565b80356001600160a01b038116811461062b57600080fd5b92915050565b600082601f830112610641578081fd5b813567ffffffffffffffff80821115610658578283fd5b60206106678182850201610a51565b838152935080840185820160005b858110156106e55781358801604080601f19838d0301121561069657600080fd5b61069f81610a51565b6106ab8c888501610614565b81529082013590878211156106bf57600080fd5b6106cd8c88848601016106f1565b81880152855250509183019190830190600101610675565b50505050505092915050565b600082601f830112610701578081fd5b813567ffffffffffffffff811115610717578182fd5b61072a601f8201601f1916602001610a51565b915080825283602082850101111561074157600080fd5b8060208401602084013760009082016020015292915050565b60006020828403121561076b578081fd5b6107758383610614565b9392505050565b60006020828403121561078d578081fd5b813567ffffffffffffffff8111156107a3578182fd5b6107af84828501610631565b949350505050565b600080604083850312156107c9578081fd5b823580151581146107d8578182fd5b9150602083013567ffffffffffffffff8111156107f3578182fd5b6107ff85828601610631565b9150509250929050565b60006020828403121561081a578081fd5b5035919050565b60008282518085526020808601955080818302840101818601855b8481101561088057858303601f190189528151805115158452840151604085850181905261086c8186018361088d565b9a86019a945050509083019060010161083c565b5090979650505050505050565b600081518084526108a5816020860160208601610a78565b601f01601f19169290920160200192915050565b600082516108cb818460208701610a78565b9190910192915050565b6001600160a01b0391909116815260200190565b6000602082526107756020830184610821565b90815260200190565b60208082526026908201527f4d756c746963616c6c206167677265676174653a2073746174696363616c6c2060408201526519985a5b195960d21b606082015260800190565b60208082526021908201527f4d756c746963616c6c32206167677265676174653a2063616c6c206661696c656040820152601960fa1b606082015260800190565b6020808252818101527f4d756c746963616c6c206167677265676174653a2063616c6c206661696c6564604082015260600190565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b82811015610a1b57605f19888703018452610a0986835161088d565b955092840192908401906001016109ed565b509398975050505050505050565b600084825283602083015260606040830152610a486060830184610821565b95945050505050565b60405181810167ffffffffffffffff81118282101715610a7057600080fd5b604052919050565b60005b83811015610a93578181015183820152602001610a7b565b83811115610aa2576000848401525b5050505056fea26469706673582212205f428655786ae5a1916268a4733805180cfe928fe48641f51aa06988fbc6391064736f6c63430007010033";

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
