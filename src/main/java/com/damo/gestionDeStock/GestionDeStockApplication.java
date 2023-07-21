package com.damo.gestionDeStock;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionDeStockApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Starting Code");
	}
}
