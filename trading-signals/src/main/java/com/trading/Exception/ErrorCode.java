package com.trading.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BINANCE_API_ERROR("Failed to retrieve account information from Binance");
    private String message;
}
