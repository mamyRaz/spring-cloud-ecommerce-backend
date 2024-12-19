package org.mamy.customerservice.repositories;


import org.mamy.customerservice.config.CustomerProjection;
import org.mamy.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = CustomerProjection.class) : CustomerProjection est la classe de configuration de projection des champs
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
