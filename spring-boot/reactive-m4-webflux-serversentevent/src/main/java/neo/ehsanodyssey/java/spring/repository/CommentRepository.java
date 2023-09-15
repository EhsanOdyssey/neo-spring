package neo.ehsanodyssey.java.spring.repository;

import neo.ehsanodyssey.java.spring.model.Comment;
import reactor.core.publisher.Flux;

public interface CommentRepository {

    Flux<Comment> findAll();

}
