package neo.ehsanodyssey.java.spring.repository;

import neo.ehsanodyssey.java.spring.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
