package com.trading.Core;

import com.trading.Indicator.BreakoutIndicator;
import com.trading.Indicator.EmaIndicator;
import com.trading.Indicator.RsiIndicator;
import com.trading.websocket.MarketTick;

public class MarketState {
    private EmaIndicator ema20 = new EmaIndicator(20);
    private EmaIndicator ema50 = new EmaIndicator(50);
    private RsiIndicator rsi14 = new RsiIndicator(14);
    private BreakoutIndicator breakout20 = new BreakoutIndicator(20);
    public void update(MarketTick tick) {
        if (!tick.isClosed()) {
            return;
        }
        ema20.update(tick.getClose());
        ema50.update(tick.getClose());
        rsi14.update(tick.getClose());
        breakout20.update(tick.getHigh());
    }
    public double getEma20() {
        return ema20.getValue();
    }
    public double getEma50() {
        return ema50.getValue();
    }
    public double getRsi() {
        return rsi14.getValue();
    }
    public boolean isBullishTrend() {
        return ema20.getValue() > ema50.getValue();
    }
    public boolean isBreakout(double close) {
        return breakout20.isBreakout(close);
    }
    public boolean isReady() {
        return ema20.isReady() && ema50.isReady() && rsi14.isReady() && breakout20.isReady();
    }
}
