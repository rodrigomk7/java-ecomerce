package com.coderhouse.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EcommerceApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
