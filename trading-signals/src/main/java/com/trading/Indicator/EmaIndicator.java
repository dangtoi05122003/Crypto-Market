package com.trading.Indicator;

public class EmaIndicator {
    private final double k;
    private Double ema;
    public EmaIndicator(int period) {
        this.k = 2.0/(period + 1);
    }
    public void update(double price) {
        if(ema == null) {
            ema = price;
            return;
        }
        ema = (price - ema) * k + ema;
    }
    public double getValue() {
        return ema == null ? 0 : ema;
    }
    public boolean isReady() {
        return ema != null;
    }
}
