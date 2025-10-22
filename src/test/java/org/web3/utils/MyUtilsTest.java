package org.web3.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class MyUtilsTest {
    @Test
    @DisplayName("测试获取校验地址")
    public void testChecksumAddress() {
        String address = "0xadfa25a73e46619782317fb13352db24c18a8d57";
        String checksumAddress = ChecksumAddress.getChecksumAddress(address);
        System.out.println("checksumAddress: " + checksumAddress);
        assertTrue(ChecksumAddress.equalsAddress(address, checksumAddress), "地址不一致");
    }

  
}
