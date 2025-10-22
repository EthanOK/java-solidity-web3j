package org.web3.utils;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class GetCurrentBlockNumberTest {

    private Web3j web3j;
    Dotenv dotenv = Dotenv.load();
    String RPC_URL = dotenv.get("ALCHEMY_SEPOLIA_URL");

    @BeforeEach
    void setUp() {
        web3j = Web3j.build(new HttpService(RPC_URL));
    }

    @Test
    @DisplayName("测试Web3j连接是否正常")
    void testWeb3jConnection() throws IOException {
        // 测试连接是否正常
        assertDoesNotThrow(() -> {
            BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
            assertNotNull(blockNumber);
        }, "Web3j连接应该正常工作");

        System.out.println("Web3j连接正常");

    }

    @Test
    @DisplayName("测试获取当前区块号")
    void testGetCurrentBlockNumber() throws IOException {
        // 执行测试
        BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();

        // 验证结果
        assertNotNull(blockNumber, "区块号不应为空");
        assertTrue(blockNumber.compareTo(BigInteger.ZERO) > 0, "区块号应大于0");

        // 输出结果用于调试
        System.out.println("当前区块号: " + blockNumber);
    }

    @AfterEach
    void tearDown() {
        // 关闭Web3j实例
        if (web3j != null) {
            web3j.shutdown();
        }
    }

}
