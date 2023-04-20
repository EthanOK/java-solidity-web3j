package org.web3.utils;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.model.Multicall;
import org.web3j.model.Multicall.Call;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;
import java.math.BigInteger;
import java.util.*;

public class GETTokenURIs {
    public static Map<String, String> getTokenURI(Web3j web3j, Credentials credentials, String multicalladdress,
            String tokenAddress, String[] tokenIds) throws Exception {
        // Web3j web3j = Web3j
        // .build(new HttpService(RPC));
        // Credentials operator = Credentials.create(privatekeyOperator);
        // String multicalladdress = "0x5D0C8b801d2Fd1dEEBB5aDfA89a8609becD01D83";

        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        // 只有operator可以读
        Multicall contract = Multicall.load(multicalladdress, web3j,
                new RawTransactionManager(web3j,
                        credentials),
                contractGasProvider);
        ;

        Map<String, String> map = new HashMap<>();

        List<Call> calls = new ArrayList<>();
        for (int i = 0; i < tokenIds.length; i++) {
            Function tokenURI = new Function("tokenURI", Arrays.asList(new Uint256(new BigInteger(tokenIds[i]))),
                    Arrays.asList(new TypeReference<Utf8String>() {
                    }));
            String encodedtokenURI = FunctionEncoder.encode(tokenURI);
            byte[] callData = Numeric.hexStringToByteArray(encodedtokenURI);
            Call call = new Call(tokenAddress, callData);
            calls.add(call);
        }
        // return uint256 blockNumber, bytes[] memory returnData
        Tuple2<BigInteger, List<byte[]>> result = contract.aggregateStaticCall(calls).sendAsync().get();

        BigInteger blockNumber = result.component1();
        System.out.println("blockNumber:" + blockNumber.toString());
        List<byte[]> returnDataArray = result.component2();

        for (int i = 0; i < returnDataArray.size(); i++) {
            String bytesHex = Numeric.toHexString(returnDataArray.get(i));
            // method1: bytes decode String
            byte[] decodedValues = FunctionReturnDecoder.decodeDynamicBytes(bytesHex);
            String tokenURI = new String(decodedValues, "UTF-8");
            System.out.println("new:" + tokenURI);
            map.put(tokenIds[i], tokenURI);

        }
        return map;

    }
}
