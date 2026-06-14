package com.trading.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.trading.Utils.Signature;

@Component
public class BinanceSpotClient {
    @Value("${exchange.binance.base-url}")
    private String BASE_URL;
    @Value("${exchange.binance.API-Key}")
    private String api_key;
    @Value("${exchange.binance.Secret-Key}")
    private String api_secret;
    private final RestTemplate restTemplate = new RestTemplate();
    private String buildQuery(String params) {
        long timestamp = System.currentTimeMillis();
        if (params == null || params.isEmpty()) {
            return "timestamp=" + timestamp;
        }
        return params + "&timestamp=" + timestamp;
    }
    public String getAccount() {
        String query = buildQuery("");
        String signature = Signature.sign(query, api_secret);

        String url = BASE_URL + "/api/v3/account?" + query + "&signature=" + signature;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-MBX-APIKEY", api_key);
        
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
