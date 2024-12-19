package org.mamy.billingservice.openfeign;

import org.mamy.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 * C'est un client REST qui permet de communiquer avec le micro-service INVENTORY-SERVICE
 */
@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable String id);

    /***
     * Récupère la liste des products au format HATEOAS
     * {
     *     "_embedded": {
     *         "customers": [
     *              "id": 1,
     *              "name": "Maeva",
     *              "email": "maeva@gmail.com"
     *              "_link": {
     *                  "self": {
     *                      "href": "http://localhost:8082/products/1"
     *                  },
     *                  "customer": {
     *                      "href": "http://localhost:8082/products/1"
     *                      "templated": true
     *                  }
     *              }
     *         ]
     *     }
     * }
     * @return un objet de type PagedModel qui représente et gère le format HATEOAS
     * et c'est cette classe qui fait le mapping avec la classe Product
     */
    @GetMapping("/products")
    PagedModel<Product> findAllProducts();
}
