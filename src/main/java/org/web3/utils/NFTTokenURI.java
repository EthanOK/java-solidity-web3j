package org.web3.utils;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.utils.Numeric;
import java.math.BigInteger;
import java.util.*;

public class NFTTokenURI {
        public static Map<String, String> getTokenURI(Web3j web3j, String multicalladdress, String tokenAddress,
                        String[] tokenIds) throws Exception {
                Set<String> set = new HashSet<>(Arrays.asList(tokenIds));
                String[] uniqueTokenIds = set.toArray(new String[0]);
                Map<String, String> map = new HashMap<>();

                int len = uniqueTokenIds.length;

                DynamicStruct[] callDatas = new DynamicStruct[len];

                for (int i = 0; i < len; i++) {
                        Function tokenURI = new Function("tokenURI",
                                        Arrays.asList(new Uint256(new BigInteger(uniqueTokenIds[i]))),
                                        Arrays.asList(new TypeReference<Utf8String>() {
                                        }));
                        String encodedtokenURI = FunctionEncoder.encode(tokenURI);
                        byte[] bytesData = Numeric.hexStringToByteArray(encodedtokenURI);
                        callDatas[i] = new DynamicStruct(
                                        new Address(tokenAddress),
                                        new DynamicBytes(bytesData));
                }

                DynamicArray<DynamicStruct> callDatasArray = new DynamicArray<DynamicStruct>(DynamicStruct.class,
                                callDatas);

                Function function = new Function("aggregate", Arrays.asList(callDatasArray),
                                Arrays.asList(new TypeReference<Uint256>() {
                                }, new TypeReference<DynamicArray<DynamicBytes>>() {
                                }));

                String encodedFunction = FunctionEncoder.encode(function);
                EthCall ethCall = web3j.ethCall(
                                Transaction.createEthCallTransaction("0x0d3e02768ab63516ab5d386fad462214ca3e6a86",
                                                multicalladdress,
                                                encodedFunction),
                                DefaultBlockParameterName.LATEST)
                                .sendAsync().get();

                // 解析返回数据
                List<Type> response = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
                // Uint256 blockNumber = (Uint256) response.get(0);
                DynamicArray<DynamicBytes> returnDataArray = (DynamicArray<DynamicBytes>) response.get(1);
                if (returnDataArray.getValue().size() != len) {
                        throw new Exception("Mismatched length");
                }
                // bytes[]
                List<DynamicBytes> listBytes = returnDataArray.getValue();

                for (int i = 0; i < listBytes.size(); i++) {
                        String bytesHex = Numeric.toHexString(listBytes.get(i).getValue());
                        // method1: bytes decode String
                        byte[] decodedValues = FunctionReturnDecoder.decodeDynamicBytes(
                                        bytesHex);
                        String tokenURI = new String(decodedValues, "UTF-8");
                        map.put(uniqueTokenIds[i], tokenURI);

                        // method2:
                        // List<Type> listTokenURI = FunctionReturnDecoder.decode(bytesHex,
                        // functionString.getOutputParameters());
                        // listTokenURI.get(0).getValue();
                        // System.out.println(listTokenURI.get(0).getValue().toString());
                }
                return map;

        }
}
