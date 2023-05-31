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
    public static final String BINARY = "608060405234801561001057600080fd5b50610336806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063252dba421461003b5780635023a1f214610065575b600080fd5b61004e6100493660046100fa565b610078565b60405161005c92919061023d565b60405180910390f35b61004e61007336600461008b565b610081565b60006060915091565b5060009160609150565b6000806020838503121561009d578182fd5b823567ffffffffffffffff808211156100b4578384fd5b818501915085601f8301126100c7578384fd5b8135818111156100d5578485fd5b86602080830285010111156100e8578485fd5b60209290920196919550909350505050565b6000602080838503121561010c578182fd5b67ffffffffffffffff8084351115610122578283fd5b8335840185601f820112610134578384fd5b80358281111561014057fe5b61014d84858302016102dc565b81815284810190838601875b8481101561022e5781358601601f196040828e0382011215610179578a8bfd5b604051604081018181108b8211171561018e57fe5b604052828b01356001600160a01b03811681146101a9578c8dfd5b815260408301358a8111156101bc578c8dfd5b8084019350508d603f8401126101d0578b8cfd5b8a8301358a8111156101de57fe5b6101ee8c84601f840116016102dc565b92508083528e6040828601011115610204578c8dfd5b80604085018d85013782018b018c9052808b01919091528552509287019290870190600101610159565b50909998505050505050505050565b600060408201848352602060408185015281855180845260608601915060608382028701019350828701855b828110156102ce57878603605f1901845281518051808852885b8181101561029e578281018801518982018901528701610283565b818111156102ae578988838b0101525b50601f01601f191696909601850195509284019290840190600101610269565b509398975050505050505050565b60405181810167ffffffffffffffff811182821017156102f857fe5b60405291905056fea26469706673582212206484eaf97c460cb4b694d9924a8b31575da7f69d285c6bee80155304b3a36e1964736f6c63430007060033";

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
