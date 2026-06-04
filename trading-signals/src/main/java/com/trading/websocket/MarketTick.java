package com.trading.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MarketTick {
    public String symbol;
    public double open;
    public double high;
    public double low;
    public double close;
    public double volume;
    public long openTime;
    public long closeTime;
    public boolean closed;
}