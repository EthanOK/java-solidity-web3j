package org.web3;

import io.github.cdimascio.dotenv.Dotenv;
import org.web3.utils.GETTokenURIs;
import org.web3.utils.NFTTokenURI;
import org.web3.utils.NFTTokenURIOP;
import org.web3j.crypto.Credentials;
import org.web3j.model.IERC165;
import org.web3j.model.Multicall2Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;
import org.web3j.protocol.http.HttpService;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Main {
	// mvn web3j:generate-sources

	static Dotenv dotenv = Dotenv.load();
	static String RPC = dotenv.get("ALCHEMY_SEPOLIA_URL");
	static String privatekey = dotenv.get("private_key");
	static String privatekeyOperator = dotenv.get("private_key_operator");

	public static void main(String[] args) throws Exception {

		Credentials credentials = Credentials.create(privatekey);

		Web3j web3j = Web3j
				.build(new HttpService(RPC));

		// 获取合约地址和ABI
		String multicallAddressEveryone = "0xcA11bde05977b3631167028862bE2a173976CA11";
		String multicallAddressOwner = "0x642195ca3a8928BF8A9D063fF38B6fa8F75696e8";
		String contractAddress = "0x709B78B36b7208f668A3823c1d1992C0805E4f4d";
		String[] tokenIds = { "16", "32" };

		// 只读 任何人
		getTokenURI(web3j, multicallAddressEveryone, contractAddress, tokenIds);

		// 只读 only owner
		getTokenURI(web3j, credentials, multicallAddressOwner, contractAddress,
				tokenIds);

		// 区块链写操作

		// changeData(web3j, credentials, multicallAddressOwner);

		System.out.println("`````````````````````````````````````");

		// `````````````````````````````````````````````````
		// 公共函数只读用这个
		// Read only public function
		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		Multicall2Test contract = Multicall2Test.load(multicallAddressEveryone, web3j,
				new ReadonlyTransactionManager(web3j, null), contractGasProvider);
		BigInteger blockTimestamp = contract.getCurrentBlockTimestamp().sendAsync().get();
		System.out.println("BlockTimestamp:" + blockTimestamp);

		// 调用合约 执行写操作或有限制的读操作（需要私钥）
		// 加载智能合约
		Multicall2Test contract1 = Multicall2Test.load(multicallAddressOwner, web3j,
				new RawTransactionManager(web3j,
						credentials),
				contractGasProvider);

		BigInteger blockNumber1 = contract1.getBlockNumber().sendAsync().get();
		System.out.println("blockNumber:" + blockNumber1);

		IERC165 erc165 = IERC165.load("0x7bAFa845D7d6Ffbd40697Ead682508486e32970b", web3j,
				new ReadonlyTransactionManager(web3j, null), contractGasProvider);
		String IERC6551Account_V3 = "0x6faff5f1";
		Boolean bool = erc165.supportsInterface(Numeric.hexStringToByteArray(IERC6551Account_V3)).sendAsync().get();
		System.out.println(bool);
	}

	public static void getTokenURI(Web3j web3j, String multicalladdress, String tokenAddress,
			String[] tokenIds) {
		Map<String, String> mapsTokenURI = null;

		try {
			mapsTokenURI = NFTTokenURI.getTokenURI(web3j, multicalladdress,
					tokenAddress, tokenIds);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		for (Map.Entry<String, String> entry : mapsTokenURI.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			System.out.println("tokenId: " + key + ", tokenURI: " + value);
		}

	}

	public static void getTokenURI(Web3j web3j, Credentials credentials, String multicalladdress,
			String tokenAddress, String[] tokenIds) {
		Map<String, String> mapsTokenURI1 = null;
		try {
			mapsTokenURI1 = NFTTokenURIOP.getTokenURI(web3j, credentials, multicalladdress,
					tokenAddress, tokenIds);
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		for (Map.Entry<String, String> entry : mapsTokenURI1.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			System.out.println("tokenId: " + key + ", tokenURI: " + value);
		}
	}

	public static void changeData(Web3j web3j, Credentials credentials, String contractAddress)
			throws Exception {
		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		Multicall2Test contract = Multicall2Test.load(contractAddress, web3j,
				new RawTransactionManager(web3j,
						credentials),
				contractGasProvider);
		BigInteger blockNumber = contract.getBlockNumber().send();
		BigInteger weiValue = new BigInteger("0");
		// 同步 只有链上有结果返回还能得到hash
		// TransactionReceipt tr = contract.setNumber(blockNumber,weiValue).send();
		// System.out.println("TransactionHash:" + tr.getTransactionHash());

		CompletableFuture<TransactionReceipt> futureReceipt = contract.setNumber(blockNumber, weiValue).sendAsync();
		// 可以在这里执行一些异步的操作
		// 添加回调方法，输出交易hash
		String txHash = futureReceipt.thenApplyAsync(TransactionReceipt::getTransactionHash).get();
		System.out.println("Transaction hash: " + txHash);
		TransactionReceipt tr = futureReceipt.get();
		if (tr.isStatusOK()) {
			// 处理成功的交易结果
			System.out.println("Success");
		} else {
			// 处理失败的交易结果
			System.out.println("Failure");
		}
	}
}
