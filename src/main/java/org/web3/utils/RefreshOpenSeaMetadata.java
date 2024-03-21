package org.web3.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RefreshOpenSeaMetadata {
    static Dotenv dotenv = Dotenv.load();

    static String OPENSEA_API_KEY = dotenv.get("OPENSEA_API_KEY");

    static int numberThreads = 8;

    public static void main(String[] args) {

        List<String> tokenIdsFail = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            tokenIdsFail.add(String.valueOf(i));
        }
        // 获取程序开始时间
        long startTime = System.currentTimeMillis();

        Ticket ticket = updateOpenSeaMetadata(
                "0x60e4d786628fea6478f785a6d7e704777c86a7c6",
                "2000",
                "2100");
        // ticket.setTokenIdsFail(tokenIdsFail);
        System.out.println("获取失败：" + Arrays.toString(ticket.getTokenIdsFail().toArray()));

        excuteFail(ticket);
        // 获取程序结束时间
        long endTime = System.currentTimeMillis();

        System.out.println("Program Execution Time: " + (endTime - startTime) + " ms");

    }

    public static Ticket updateOpenSeaMetadata(String address, String startTokenId, String endTokenId) {
        // 使用多线程
        Ticket ticket = new Ticket();
        ticket.setStart(Long.valueOf(startTokenId));
        ticket.setEnd(Long.valueOf(endTokenId));
        ticket.setAddress(address);
        ticket.setOpenseaApiKey(OPENSEA_API_KEY);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numberThreads; i++) {
            Thread windows = new Thread(ticket);
            windows.start();
            threads.add(windows);
        }
        // 等待所有线程结束
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("error: thread.join()");
        }

        return ticket;

    }

    private static void excuteFail(Ticket ticket) {
        while (ticket.getTokenIdsFail().size() > 0) {
            System.out.println("失败次数：" + ticket.getTokenIdsFail().size());
            List<String> tokenIds = ticket.getTokenIdsFail();
            ticket.setTokenIdsFail(new ArrayList<>());
            processArrayList(ticket, tokenIds.toArray(new String[0]));
        }
        System.out.println("Update Success!");

    }

    private static void processArrayList(Ticket ticket, String[] tokenIds) {

        // 计算每个线程应该处理的数组部分的大小
        int chunkSize = tokenIds.length / numberThreads;
        // 创建线程数组
        Thread[] threads = new Thread[numberThreads];

        // 启动线程并处理数组的不同部分
        for (int i = 0; i < numberThreads; i++) {
            int start = i * chunkSize;
            int end = (i < numberThreads - 1) ? (i + 1) * chunkSize : tokenIds.length;

            threads[i] = new Thread(() -> processArrayPart(ticket, tokenIds, start, end));
            threads[i].start();
        }

        // 等待所有线程完成
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void processArrayPart(Ticket ticket, String[] tokenIds, int start, int end) {
        for (int i = start; i < end; i++) {
            ticket.excute(tokenIds[i]);
        }
    }
}

class Ticket implements Runnable {
    List<String> tokenIdsFail = new ArrayList<>();

    public void setTokenIdsFail(List<String> tokenIdsFail) {
        this.tokenIdsFail = tokenIdsFail;
    }

    public List<String> getTokenIdsFail() {
        return tokenIdsFail;
    }

    private long start;
    private long end;
    private String address;
    private String openseaApiKey;

    public void setOpenseaApiKey(String openseaApiKey) {
        this.openseaApiKey = openseaApiKey;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void run() {

        try {
            while (true) {
                if (start > end) {
                    break;
                }

                excute(String.valueOf(start));
                // System.out.println(Thread.currentThread().getName() + " Run:" + start);
                start++;
            }
        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("error: excute()");

        }

    }

    public void excute(String tokenId) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.opensea.io/api/v2/chain/ethereum/contract/"
                + address + "/nfts/"
                + tokenId
                + "/refresh";

        RequestBody requestBody = RequestBody.create("", MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("x-api-key", openseaApiKey)
                .build();

        Response response;

        try {
            response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();

            tokenIdsFail.add(tokenId);
        }

    }

}
