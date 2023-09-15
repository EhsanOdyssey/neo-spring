package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.model.Purchase;
import neo.ehsanodyssey.java.spring.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coin/purchase/v1")
public class PurchaseController {

    @Autowired
    private CoinbaseService coinbaseService;

    // You can use BTC-USD as name parameter
    // Please see https://developers.coinbase.com/api/v2
    @PostMapping(value = "/{name}")
    public Mono<Purchase> createPurchase(@PathVariable("name") String name) {
        return coinbaseService.createPurchase(name);
    }
}
