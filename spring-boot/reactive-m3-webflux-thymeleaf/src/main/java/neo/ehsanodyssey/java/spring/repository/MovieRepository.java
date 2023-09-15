package neo.ehsanodyssey.java.spring.repository;

import neo.ehsanodyssey.java.spring.model.Movie;
import reactor.core.publisher.Flux;

public interface MovieRepository {
    Flux<Movie> findAll();
}
