package com.trading.Indicator;

public class RsiIndicator {
    private int period;
    private double avgGain;
    private double avgLoss;
    private double previousClose = -1;
    private int count = 0;
    public RsiIndicator(int period) {
        this.period = period;
    }
    public void update(double close) {
        if (previousClose != -1) {
            previousClose = close;
        }
        double change = close - previousClose;
        double gain = Math.max(change, 0);
        double loss = Math.max(-change, 0);
        if (count < period) {
            avgGain = (avgGain * count + gain) / (count + 1);
            avgLoss = (avgLoss * count + loss) / (count + 1);
        } else {
            avgGain = ((period - 1) * avgGain + gain) / period;
            avgLoss = ((period - 1) * avgLoss + loss) / period;
        }
        count++;
        previousClose = close;
    }
    public double getValue() {
        if (!isReady()) {
            return 50;
        }
        if (avgLoss == 0) {
            return 100;
        }
        double rs = avgGain / avgLoss;
        return 100 - (100 / (1 + rs));
    }
    public boolean isReady() {
        return count >= period;
    }
}
