package neo.ehsanodyssey.java.spring.reactive.repository;

import neo.ehsanodyssey.java.spring.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserReactiveRepository extends ReactiveMongoRepository<User, String> {
}
