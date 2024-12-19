package org.mamy.customerservice;

import org.mamy.customerservice.config.CustomerConfigParams;
import org.mamy.customerservice.entities.Customer;
import org.mamy.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// Activer la gestion des fichiers de configuration (.properties), qui est prise en charge par la classe "CustomerConfigParams"
@EnableConfigurationProperties(CustomerConfigParams.class)
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("Maeva").email("maeva@gmail.com").build());
            customerRepository.save(Customer.builder().name("Joseph").email("joseph@gmail.com").build());
            customerRepository.save(Customer.builder().name("Maria").email("maria@gmail.com").build());
            customerRepository.save(Customer.builder().name("Jo").email("jo@gmail.com").build());

            customerRepository.findAll().forEach(customer -> {
                System.out.println("=================================");
                System.out.println(customer.toString());
            });
        };
    }
}
