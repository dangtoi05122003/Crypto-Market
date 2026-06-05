package com.trading.Indicator;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreakoutIndicator {
    private int period;
    private Queue<Double> highs = new ArrayDeque<>();
    private double prev = 0;
    private double curr = 0;
    public BreakoutIndicator(int period) {
        this.period = period;
    }
    public void update(double high) {
        prev = curr;
        highs.add(high);
        if (highs.size() > period) {
            highs.poll();
        }
        curr = highs.stream().mapToDouble(Double::doubleValue).max().orElse(high);
    }
    public boolean isBreakout(double close) {
        return close > prev * 1.001;
    }
    public double getCurrentHigh() {
        return curr;
    }
    public boolean isReady() {
        return highs.size() >= period;
    }
}
