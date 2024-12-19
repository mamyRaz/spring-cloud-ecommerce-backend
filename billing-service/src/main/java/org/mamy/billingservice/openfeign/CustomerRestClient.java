package org.mamy.billingservice.openfeign;

import org.mamy.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 * C'est un client REST qui permet de communiquer avec le micro-service CUSTOMER-SERVICE
 */
@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable Long id);

    /***
     * Récupère la liste des customers au format HATEOAS
     * {
     *     "_embedded": {
     *         "customers": [
     *              "id": 1,
     *              "name": "Maeva",
     *              "email": "maeva@gmail.com"
     *              "_link": {
     *                  "self": {
     *                      "href": "http://localhost:8081/customers/1"
     *                  },
     *                  "customer": {
     *                      "href": "http://localhost:8081/customers/1"
     *                      "templated": true
     *                  }
     *              }
     *         ]
     *     }
     * }
     * @return un objet de type PagedModel qui représente et gère le format HATEOAS
     * et c'est cette classe qui fait le mapping avec la classe Customer
     */
    @GetMapping("/customers")
    PagedModel<Customer> findAllCustomers();
}