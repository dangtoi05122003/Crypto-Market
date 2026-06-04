package com.trading.websocket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import com.trading.Core.MarketParser;
import com.trading.Event.MarketPublisher;

@Component
public class BinanceClient extends WebSocketClient {
    private final MarketPublisher publisher;
    private final MarketParser parser = new MarketParser();
    public BinanceClient(MarketPublisher publisher) throws Exception {
        super(new URI("wss://stream.binance.com:9443/ws/btcusdt@kline_1m"));
        this.publisher = publisher;
    }
    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to Binance");
    }
    @Override
    public void onMessage(String message) {
        MarketTick tick = parser.parse(message);
        publisher.publish(tick);
    }
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed: " + reason);
    }
    @Override
    public void onError(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
