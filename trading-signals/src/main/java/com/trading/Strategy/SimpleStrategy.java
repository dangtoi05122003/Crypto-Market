package com.trading.Strategy;

import org.springframework.stereotype.Component;

import com.trading.Core.MarketContext;
import com.trading.Enum.Signal;

@Component
public class SimpleStrategy implements strategy{
    @Override
    public Signal evaluate(MarketContext marketContext) {
        double rsi = marketContext.getRsi();
        double price = marketContext.getClose();
        double ema20 = marketContext.getEma20();
        double ema50 = marketContext.getEma50();
        boolean uptrend = ema20 > ema50;
        boolean downtrend = ema20 < ema50;
        boolean pullbackBuy = uptrend && rsi >= 40 && rsi <= 65 && price <= ema20 * 1.005 && price >= ema50;
        boolean breakoutBuy = uptrend && marketContext.isBreakout() && rsi > 50;
        if (pullbackBuy || breakoutBuy) {
            return Signal.BUY;
        }
        boolean trendBroken = price < ema50 && rsi < 50;
        boolean strongWeak = rsi < 30;
        boolean confirmedDowntrend = downtrend && rsi < 45;
        if (trendBroken || strongWeak || confirmedDowntrend) {
            return Signal.SELL;
        }
        return Signal.HOLD;
    }
}
