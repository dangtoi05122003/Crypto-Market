package com.trading.Service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.Client.BinanceSpotClient;
import com.trading.Exception.AppException;
import com.trading.Exception.ErrorCode;

@Service
public class AccountService {
    private final BinanceSpotClient binanceSpotClient;
    private final ObjectMapper objectMapper;
    public AccountService(BinanceSpotClient binanceSpotClient, ObjectMapper objectMapper) {
        this.binanceSpotClient = binanceSpotClient;
        this.objectMapper = objectMapper;
    }
    private double getBalance(String asset) {
        try {
            String account = binanceSpotClient.getAccount();
            JsonNode root = objectMapper.readTree(account);
            JsonNode balances = root.get("balances");
            for (JsonNode balance : balances) {
                if (asset.equals(balance.get("asset").asText())) {
                    return balance.get("free").asDouble();
                }
            }
            return 0.0;
        } catch (Exception e) {
            throw new AppException(ErrorCode.BINANCE_API_ERROR);
        }
    }
    public double getUsdtBalance() {
        return getBalance("USDT");
    }
}
