package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
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
    public static final String BINARY = "608060405234801561001057600080fd5b5061019f806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80631f8bb20e14610046578063338eb90b1461005a578063fecceafa14610068575b600080fd5b610058610054366004610079565b5050565b005b6100586100543660046100ee565b610058610076366004610151565b50565b6000806020838503121561008c57600080fd5b823567ffffffffffffffff808211156100a457600080fd5b818501915085601f8301126100b857600080fd5b8135818111156100c757600080fd5b8660208260051b85010111156100dc57600080fd5b60209290920196919550909350505050565b6000806020838503121561010157600080fd5b823567ffffffffffffffff8082111561011957600080fd5b818501915085601f83011261012d57600080fd5b81358181111561013c57600080fd5b8660208260061b85010111156100dc57600080fd5b60006040828403121561016357600080fd5b5091905056fea264697066735822122051eaf6ac55fe4e34f6557cd749345a60d1d21fdd92a1c3310bebc9f4f414e64e64736f6c63430008110033";

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
