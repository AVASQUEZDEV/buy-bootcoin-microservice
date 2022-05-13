package com.nttdata.buybitcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BuyBitcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyBitcoinApplication.class, args);
	}

}
