package org.mamy.customerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 * Ce contrôleur permet de tester si ce micro-service customer-service,
 * arrive bien à récupérer des paramètres dans les fichiers de configuration depuis le service de configuration
 */

    // Ce contrôleur va être à nouveau instancié à chaque fois que la config est rafraichie.
    // Avec actuator un scope de l'application pourrait être mis à jour à chaud sans redémarrage,
    // en faisant appel à http://localhost:8081/actuator/refresh
    // Ce qui fait que toutes les classes se retrouvant avec @RefreshScope vont être à jour automatiquement
    // en réinjectant les nouvelles valeurs de configuration (ici global.params.p1, customerConfigParams etc...)
@RefreshScope
@RestController
public class TestConfigController {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private CustomerConfigParams customerConfigParams;

    public TestConfigController(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }

    /***
     * Ce endpoint permet de tester la récupération des paramètres globaux
     * (global.params.p1 et global.params.p2)
     * dans le fichier de configuration "application.properties",
     * via l'annotation @Value ci-dessus
     * @return un objet JSON contenant les valeurs de ces 2 paramètres p1 et p2
     */
    @GetMapping("/testConfigGlobal")
    public Map<String, String> testConfigGlobal() {
        return Map.of("p1", p1, "p2", p2);
    }

    /***
     * Ce endpoint permet de tester la récupération des paramètres
     * (customer.params.x et customer.params.y)
     * dans le fichier de configuration "customer-service.properties",
     * et dont la récupération est gérée par la classe record "CustomerConfigParams"
     * via l'annotation @ConfigurationProperties
     * @return un objet JSON contenant les valeurs de ces 2 paramètres x et y
     */
    @GetMapping("/testConfigCustomer")
    public CustomerConfigParams testConfigCustomer() {
        return customerConfigParams;
    }
}
