package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class TestArray extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506101d5806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80631f8bb20e14610051578063338eb90b14610065578063deee8a1914610051578063fecceafa14610073575b600080fd5b61006361005f3660046100d0565b5050565b005b61006361005f366004610112565b610063610081366004610187565b50565b60008083601f84011261009657600080fd5b50813567ffffffffffffffff8111156100ae57600080fd5b6020830191508360208260051b85010111156100c957600080fd5b9250929050565b600080602083850312156100e357600080fd5b823567ffffffffffffffff8111156100fa57600080fd5b61010685828601610084565b90969095509350505050565b6000806020838503121561012557600080fd5b823567ffffffffffffffff8082111561013d57600080fd5b818501915085601f83011261015157600080fd5b81358181111561016057600080fd5b8660208260061b850101111561017557600080fd5b60209290920196919550909350505050565b60006040828403121561019957600080fd5b5091905056fea2646970667358221220d15f98656ea3fd872197b600ce7d491bd80260fa4e37673faff57990dd0b819564736f6c63430008110033";

    public static final String FUNC_GET = "get";

    public static final String FUNC_SETORDER = "setOrder";

    public static final String FUNC_SETORDERS = "setOrders";

    public static final String FUNC_SETORDERSS = "setOrderss";

    @Deprecated
    protected TestArray(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TestArray(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TestArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TestArray(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> get(List<Array> param0) {
        final Function function = new Function(
                FUNC_GET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Array>(Array.class, param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOrder(Order param0) {
        final Function function = new Function(
                FUNC_SETORDER, 
                Arrays.<Type>asList(param0), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOrders(List<Order> param0) {
        final Function function = new Function(
                FUNC_SETORDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Order>(Order.class, param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOrderss(List<Order> param0) {
        final Function function = new Function(
                FUNC_SETORDERSS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Order>(Order.class, param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TestArray load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestArray(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TestArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestArray(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TestArray load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TestArray(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TestArray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TestArray(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TestArray> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestArray.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TestArray> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestArray.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TestArray> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestArray.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TestArray> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestArray.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Array extends DynamicStruct {
        public List<BigInteger> addresss;

        public Array(List<BigInteger> addresss) {
            super(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                            org.web3j.abi.datatypes.generated.Uint256.class,
                            org.web3j.abi.Utils.typeMap(addresss, org.web3j.abi.datatypes.generated.Uint256.class)));
            this.addresss = addresss;
        }

        public Array(DynamicArray<Uint256> addresss) {
            super(addresss);
            this.addresss = addresss.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
        }
    }

    public static class Order extends StaticStruct {
        public String from;

        public String to;

        public Order(String from, String to) {
            super(new org.web3j.abi.datatypes.Address(160, from), 
                    new org.web3j.abi.datatypes.Address(160, to));
            this.from = from;
            this.to = to;
        }

        public Order(Address from, Address to) {
            super(from, to);
            this.from = from.getValue();
            this.to = to.getValue();
        }
    }
}
