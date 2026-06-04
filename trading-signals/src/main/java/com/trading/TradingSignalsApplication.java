package com.trading;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.trading.websocket.BinanceClient;

@SpringBootApplication
@EnableAsync
public class TradingSignalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingSignalsApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(BinanceClient binanceClient) {
		return args -> { binanceClient.connect();};
	}
}
