package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class Multicall extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506103a3806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063252dba421461003b5780635023a1f214610069575b600080fd5b6100526100493660046100f1565b60006060915091565b60405161006092919061025e565b60405180910390f35b6100526100773660046102f8565b5060009160609150565b634e487b7160e01b600052604160045260246000fd5b6040805190810167ffffffffffffffff811182821017156100ba576100ba610081565b60405290565b604051601f8201601f1916810167ffffffffffffffff811182821017156100e9576100e9610081565b604052919050565b6000602080838503121561010457600080fd5b823567ffffffffffffffff8082111561011c57600080fd5b818501915085601f83011261013057600080fd5b81358181111561014257610142610081565b8060051b6101518582016100c0565b918252838101850191858101908984111561016b57600080fd5b86860192505b83831015610251578235858111156101895760008081fd5b86016040601f19828d0381018213156101a25760008081fd5b6101aa610097565b838b01356001600160a01b03811681146101c45760008081fd5b815283830135898111156101d85760008081fd5b8085019450508d603f8501126101ee5760008081fd5b8a8401358981111561020257610202610081565b6102128c84601f840116016100c0565b92508083528e848287010111156102295760008081fd5b808486018d85013760009083018c0152808b0191909152845250509186019190860190610171565b9998505050505050505050565b600060408201848352602060408185015281855180845260608601915060608160051b87010193508287016000805b838110156102e957888703605f1901855282518051808952835b818110156102c2578281018901518a82018a015288016102a7565b508881018801849052601f01601f191690970186019650938501939185019160010161028d565b50949998505050505050505050565b6000806020838503121561030b57600080fd5b823567ffffffffffffffff8082111561032357600080fd5b818501915085601f83011261033757600080fd5b81358181111561034657600080fd5b8660208260051b850101111561035b57600080fd5b6020929092019691955090935050505056fea2646970667358221220b11a5c32a246b67ee1abcc73c370905abd915bddd0df194302d47ad98387feae64736f6c63430008110033";

    public static final String FUNC_AGGREGATE = "aggregate";

    public static final String FUNC_AGGREGATESTATICCALL = "aggregateStaticCall";

    @Deprecated
    protected Multicall(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Multicall(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Multicall(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Multicall(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    @Deprecated
    public static Multicall load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Multicall load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Multicall(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Multicall load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Multicall(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Multicall load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Multicall(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Multicall> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Multicall> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Multicall.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Multicall> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Multicall.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
}
