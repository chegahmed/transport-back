package com.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportBackApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TransportBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
