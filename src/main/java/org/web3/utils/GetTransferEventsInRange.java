package org.web3.utils;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.mysql.ConncetDB;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.utils.Numeric;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

public class GetTransferEventsInRange extends WebSocketClient {
    public GetTransferEventsInRange(URI serverUri) {
        super(serverUri);
        // TODO Auto-generated constructor stub
    }

    static Dotenv dotenv = Dotenv.load();
    static String INFURA_WSS_MAIN = dotenv.get("INFURA_WSS_MAIN");
    static Connection connection = ConncetDB.getConnect();
    static final int RECONNECT_INTERVAL = 1000; // 初始重连间隔
    static String subscribeRequest = "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\",\"params\":[{\"topics\":[\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\"]}],\"id\":1}";
    private static WebSocketClient client;
    static String lastBlockNumber;

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        System.out.println("opened connection");
        send(subscribeRequest);

    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        // handleMessage
        // handleMessage(message);

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);

    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        try {
            client = new GetTransferEventsInRange(new URI(INFURA_WSS_MAIN));
            client.connect();

            Thread.sleep(10000);
            client.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
