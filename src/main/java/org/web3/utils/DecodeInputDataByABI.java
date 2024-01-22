package org.web3.utils;

import net.osslabz.evm.abi.decoder.AbiDecoder;
import net.osslabz.evm.abi.decoder.DecodedFunctionCall;

import java.io.IOException;
import java.util.List;

public class DecodeInputDataByABI {

    // 复杂的结构体类型不能解析出来

    public static void main(String[] args) throws IOException {
        String abiFilePath = "src/main/abi/ygme.json";

        String inputData = "0xdf791e500000000000000000000000003ee6ffbc201084330962e39e9e4faf6df68eb1f10000000000000000000000006278a1e803a76796a3a1f7f6344fe874ebfe94b20000000000000000000000000000000000000000000000000000000000000002";
        AbiDecoder ygme = new AbiDecoder(abiFilePath);
        DecodedFunctionCall decodedFunctionCall = ygme.decodeFunctionCall(inputData);

        List<DecodedFunctionCall.Param> datas = decodedFunctionCall.getParamList();

        // 遍历 datas
        for (int i = 0; i < datas.size(); i++) {
            System.out.println(datas.get(i).getName());
            System.out.println(datas.get(i).getType());
            System.out.println(datas.get(i).getValue());
        }

        // System.out.println(datas);

    }
}
