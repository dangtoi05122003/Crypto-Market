package com.trading.Service;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.trading.Event.MarketEvent;
import com.trading.websocket.MarketTick;

@Service
public class MarketService {
    @Async
    @EventListener
    public void MarketPrinter(MarketEvent event) {
        MarketTick tick = event.getTick();
        System.out.println(tick);
    }
}
