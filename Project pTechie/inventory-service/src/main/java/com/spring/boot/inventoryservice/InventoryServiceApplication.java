package com.spring.boot.inventoryservice;

import com.spring.boot.inventoryservice.model.Inventory;
import com.spring.boot.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDat(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory1 = Inventory
					.builder()
					.skuCode("iPhone13")
					.quantity(100)
					.build();

			Inventory inventory2 = Inventory
					.builder()
					.skuCode("iPhone13-Red")
					.quantity(0)
					.build();

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
    }
}
