package com.trading.Core;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketContext {
    private double rsi;
    private double ema20;
    private double ema50;
    private double close;
    private boolean breakout;
}
