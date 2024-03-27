package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;
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
 * <p>Generated with web3j version 4.11.0.
 */
@SuppressWarnings("rawtypes")
public class TestArray extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b506102618061001d5f395ff3fe608060405260043610610054575f3560e01c806306fdde03146100585780631f8bb20e14610067578063338eb90b14610085578063b7d52a2514610067578063b858183f1461009f578063fecceafa146100c4575b5f80fd5b348015610063575f80fd5b505b005b348015610072575f80fd5b50610065610081366004610129565b5050565b348015610090575f80fd5b50610065610081366004610168565b6100b26100ad3660046101d7565b505f90565b60405190815260200160405180910390f35b3480156100cf575f80fd5b506100656100de366004610215565b50565b5f8083601f8401126100f1575f80fd5b50813567ffffffffffffffff811115610108575f80fd5b6020830191508360208260051b8501011115610122575f80fd5b9250929050565b5f806020838503121561013a575f80fd5b823567ffffffffffffffff811115610150575f80fd5b61015c858286016100e1565b90969095509350505050565b5f8060208385031215610179575f80fd5b823567ffffffffffffffff80821115610190575f80fd5b818501915085601f8301126101a3575f80fd5b8135818111156101b1575f80fd5b8660208260061b85010111156101c5575f80fd5b60209290920196919550909350505050565b5f602082840312156101e7575f80fd5b813567ffffffffffffffff8111156101fd575f80fd5b82016080818503121561020e575f80fd5b9392505050565b5f60408284031215610225575f80fd5b5091905056fea26469706673582212205f87abfc7dadb3fe9fc4a0707762ec761d576243ac40f7ce56563be4c50d90c164736f6c63430008140033";

    public static final String FUNC_EXACTINPUT = "exactInput";

    public static final String FUNC_GET = "get";

    public static final String FUNC_NAME = "name";

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

    public RemoteFunctionCall<TransactionReceipt> exactInput(ExactInputParams params, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXACTINPUT, 
                Arrays.<Type>asList(params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
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

    public static class ExactInputParams extends DynamicStruct {
        public byte[] path;

        public String recipient;

        public BigInteger amountIn;

        public BigInteger amountOutMinimum;

        public ExactInputParams(byte[] path, String recipient, BigInteger amountIn, BigInteger amountOutMinimum) {
            super(new org.web3j.abi.datatypes.DynamicBytes(path), 
                    new org.web3j.abi.datatypes.Address(160, recipient), 
                    new org.web3j.abi.datatypes.generated.Uint256(amountIn), 
                    new org.web3j.abi.datatypes.generated.Uint256(amountOutMinimum));
            this.path = path;
            this.recipient = recipient;
            this.amountIn = amountIn;
            this.amountOutMinimum = amountOutMinimum;
        }

        public ExactInputParams(DynamicBytes path, Address recipient, Uint256 amountIn, Uint256 amountOutMinimum) {
            super(path, recipient, amountIn, amountOutMinimum);
            this.path = path.getValue();
            this.recipient = recipient.getValue();
            this.amountIn = amountIn.getValue();
            this.amountOutMinimum = amountOutMinimum.getValue();
        }
    }

    public static class Array extends DynamicStruct {
        public List<BigInteger> addresss;

        public String account;

        public Array(List<BigInteger> addresss, String account) {
            super(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                            org.web3j.abi.datatypes.generated.Uint256.class,
                            org.web3j.abi.Utils.typeMap(addresss, org.web3j.abi.datatypes.generated.Uint256.class)), 
                    new org.web3j.abi.datatypes.Address(160, account));
            this.addresss = addresss;
            this.account = account;
        }

        public Array(@Parameterized(type = Uint256.class) DynamicArray<Uint256> addresss, Address account) {
            super(addresss, account);
            this.addresss = addresss.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
            this.account = account.getValue();
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
