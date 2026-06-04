package com.trading.Event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.trading.websocket.MarketTick;

@Component
public class MarketPublisher {
    private final ApplicationEventPublisher publisher;
    public MarketPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publish(MarketTick tick) {
        publisher.publishEvent(new MarketEvent(tick));
    }
}