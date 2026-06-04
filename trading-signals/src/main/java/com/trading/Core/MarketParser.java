package com.trading.Core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.websocket.MarketTick;

public class MarketParser {
    private final ObjectMapper mapper = new ObjectMapper();
    public MarketTick parse(String message) {
        try {
            JsonNode root = mapper.readTree(message);
            JsonNode k = root.get("k");
            return new MarketTick(
                root.get("s").asText(),
                k.get("o").asDouble(),
                k.get("h").asDouble(),
                k.get("l").asDouble(),
                k.get("c").asDouble(),
                k.get("v").asDouble(),
                k.get("t").asLong(),
                k.get("T").asLong(),
                k.get("x").asBoolean()
            );
        } catch (Exception e) {
            System.out.println("Parse error: " + message);
            return null;
        }
    }
}