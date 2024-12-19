package org.mamy.inventoryservice;

import org.mamy.inventoryservice.entities.Product;
import org.mamy.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Computer ASUS").price(2500.0).quantity(5).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Computer MSI").price(1199.0).quantity(2).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Printer HP").price(65.0).quantity(12).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Iphone 16").price(1490.0).quantity(8).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Samsung S24").price(1240.0).quantity(5).build());

			productRepository.findAll().forEach(product -> {
				System.out.println("=====================================================");
				System.out.println(product.toString());
			});
		};
	}

}
