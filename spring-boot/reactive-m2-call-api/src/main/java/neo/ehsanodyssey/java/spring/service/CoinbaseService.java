package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.model.CoinBaseResponse;
import neo.ehsanodyssey.java.spring.model.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinbaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);

    Mono<Purchase> createPurchase(String priceName);

    Mono<Purchase> getPurchaseById(String id);

    Flux<Purchase> listAllPurchases();
}
