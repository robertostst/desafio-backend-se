package br.com.softexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SoftexpertRateioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftexpertRateioApplication.class, args);
	}

}
