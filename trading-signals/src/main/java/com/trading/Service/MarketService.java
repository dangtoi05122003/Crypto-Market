package com.trading.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.trading.Core.MarketContext;
import com.trading.Core.MarketState;
import com.trading.Enum.Signal;
import com.trading.Event.MarketEvent;
import com.trading.Strategy.strategy;
import com.trading.websocket.MarketTick;

@Service
public class MarketService {
    @Autowired
    private strategy strategy;
    @Async
    @EventListener
    public void MarketPrinter(MarketEvent event) {
        MarketTick tick = event.getTick();
        MarketState state = new MarketState();
        state.update(tick);
        if(!state.isReady()) return;
        MarketContext marketContext = MarketContext.builder()
                .close(tick.getClose())
                .ema20(state.getEma20())
                .ema50(state.getEma50())
                .rsi(state.getRsi())
                .breakout(state.isBreakout(tick.getClose()))
                .build();
        Signal signal = strategy.evaluate(marketContext);
        System.out.println("SIGNAL: " + signal);
    }
}
