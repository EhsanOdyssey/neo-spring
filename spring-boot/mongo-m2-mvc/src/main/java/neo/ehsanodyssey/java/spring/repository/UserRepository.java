package neo.ehsanodyssey.java.spring.repository;

import neo.ehsanodyssey.java.spring.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
