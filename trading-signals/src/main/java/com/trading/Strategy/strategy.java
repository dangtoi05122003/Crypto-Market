package com.trading.Strategy;

import com.trading.Core.MarketContext;
import com.trading.Enum.Signal;

public interface strategy {
    Signal evaluate(MarketContext marketContext);
}
