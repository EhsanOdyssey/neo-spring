package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.dto.PageRequestImpl;
import neo.ehsanodyssey.java.spring.dto.UserModel;
import neo.ehsanodyssey.java.spring.model.User;
import neo.ehsanodyssey.java.spring.service.UserService;
import neo.ehsanodyssey.java.spring.service.UserTransactionReactiveService;
import neo.ehsanodyssey.java.spring.service.ZipsAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/")
public class ZipsAggregationController {

    private final ZipsAggregationService service;

    public ZipsAggregationController(ZipsAggregationService service) {
        this.service = service;
    }

    @GetMapping("/zips/spg")
    public ResponseEntity getStatesHavePopGreaterThanAndSorted(@RequestParam Integer limit) {
        return ResponseEntity.ok(service.getStatesHavePopGreaterThanAndSorted(limit));
    }

    @GetMapping("/zips/sla")
    public ResponseEntity getStateWithLowestAvgCityPopIsND(@RequestParam Integer limit) {
        return ResponseEntity.ok(service.getStateWithLowestAvgCityPopIsND(limit));
    }

    @GetMapping("/zips/mtmd")
    public ResponseEntity getMaxTXAndMinDC() {
        return ResponseEntity.ok(service.getMaxTXAndMinDC());
    }
}
