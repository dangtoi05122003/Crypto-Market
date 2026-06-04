package com.trading.Event;

import com.trading.websocket.MarketTick;

public class MarketEvent {
    private final MarketTick tick;
    public MarketEvent(MarketTick tick) {
        this.tick = tick;
    }
    public MarketTick getTick() {
        return tick;
    }
}