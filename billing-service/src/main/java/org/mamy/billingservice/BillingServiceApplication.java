package org.mamy.billingservice;

import org.mamy.billingservice.entities.Bill;
import org.mamy.billingservice.entities.ProductItem;
import org.mamy.billingservice.model.Customer;
import org.mamy.billingservice.model.Product;
import org.mamy.billingservice.openfeign.CustomerRestClient;
import org.mamy.billingservice.openfeign.ProductRestClient;
import org.mamy.billingservice.repositories.BillRepository;
import org.mamy.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			CustomerRestClient customerRestClient,
			ProductRestClient productRestClient) {

		return args -> {
			Collection<Customer> customers = customerRestClient.findAllCustomers().getContent();
			Collection<Product> products = productRestClient.findAllProducts().getContent();

			customers.forEach(customer -> {
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customer.getId())
						.build();
				billRepository.save(bill);

				products.forEach(product -> {
					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(product.getId())
							.quantity(1+new Random().nextInt(10))
							.unitPrice(product.getPrice())
							.build();
					productItemRepository.save(productItem);
				});
			});
		};
	}
}
