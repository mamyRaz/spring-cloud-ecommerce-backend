package org.mamy.customerservice.config;

import org.mamy.customerservice.entities.Customer;
import org.springframework.data.rest.core.config.Projection;

//@Projection(name = "customerprojection" , types = Customer.class)
public interface CustomerProjection {
    Long getId();
    String getName();
    String getEmail();
}
