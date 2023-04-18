package org.web3.utils;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.model.Multicall2;
import org.web3j.model.Multicall2.Call;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.*;

public class NFTTokenURIOP {
	public static Map<String, String> getTokenURI(Web3j web3j, Credentials credentials, String multicalladdress,
			String tokenAddress, String[] tokenIds) throws Exception {
		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		// 只读
		Multicall2 contract = Multicall2.load(multicalladdress, web3j,
				new RawTransactionManager(web3j,
						credentials),
				contractGasProvider);
		;

		Set<String> set = new HashSet<>(Arrays.asList(tokenIds));
		String[] uniqueTokenIds = set.toArray(new String[0]);
		Map<String, String> map = new HashMap<>();

		int len = uniqueTokenIds.length;

		// Call(String target, byte[] callData)
		// RemoteFunctionCall<Tuple2<BigInteger, List<byte[]>>>
		// aggregateStaticCall(List<Call> calls)

		List<Call> calls = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			Function tokenURI = new Function("tokenURI", Arrays.asList(new Uint256(new BigInteger(uniqueTokenIds[i]))),
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
			map.put(uniqueTokenIds[i], tokenURI);

		}
		return map;

	}
}
