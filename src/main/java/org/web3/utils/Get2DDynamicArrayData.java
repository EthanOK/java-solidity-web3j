package org.web3.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.NumericType;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;

public class Get2DDynamicArrayData {

    public static void main(String[] args) {
        // List<Uint256> a = new ArrayList<>();
        // a.add(new Uint256(22));
        // // List<List<Uint256>> aa = new ArrayList<>();
        // // aa.add(a);
        // DynamicArray<Uint256> b = new DynamicArray<>(a);
        // List<DynamicArray<Uint256>> bb = new ArrayList<>();
        // bb.add(b);
        // DynamicArray<DynamicArray<Uint256>> bbb = new DynamicArray<>(b);
        // // System.out.println(b.getTypeAsString());
        // // System.out.println(bb.getTypeAsString());
        // String data = TypeEncoder.encode(bbb);
        // System.out.println(data);
    }

}
