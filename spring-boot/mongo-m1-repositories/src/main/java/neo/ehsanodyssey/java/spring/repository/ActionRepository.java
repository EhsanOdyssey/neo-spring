package neo.ehsanodyssey.java.spring.repository;

import neo.ehsanodyssey.java.spring.model.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionRepository extends MongoRepository<Action, String> {

}