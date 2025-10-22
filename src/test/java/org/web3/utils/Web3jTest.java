package org.web3.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import io.github.cdimascio.dotenv.Dotenv;

public class Web3jTest {
    private Web3j web3j;
    Dotenv dotenv = Dotenv.load();
    String RPC_URL = dotenv.get("ALCHEMY_SEPOLIA_URL");

    @BeforeEach
    void setUp() {
        web3j = Web3j.build(new HttpService(RPC_URL));
    }

    @Test
    @DisplayName("测试获取链ID")
    void testWeb3jConnection() throws IOException {
        // 测试连接是否正常
        assertDoesNotThrow(() -> {

            BigInteger chainId = web3j.ethChainId().send().getChainId();
            System.out.println("链ID: " + chainId);
        }, "Web3j连接应该正常工作");

        System.out.println("Web3j连接正常");
    }
}
