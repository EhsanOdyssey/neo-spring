package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.dto.UserModel;
import neo.ehsanodyssey.java.spring.model.EmailAddress;
import neo.ehsanodyssey.java.spring.model.User;
import neo.ehsanodyssey.java.spring.reactive.repository.UserReactiveRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTransactionReactiveService {

    private UserReactiveRepository userRepository;
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public UserTransactionReactiveService(UserReactiveRepository userRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
        if (!reactiveMongoTemplate.collectionExists(User.class).block()) {
            reactiveMongoTemplate.createCollection(User.class);
        }
        this.userRepository = userRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Flux<User> insertReactive(List<UserModel> userModels) {
        List<User> batch = new ArrayList<>();
        for (UserModel model: userModels) {
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.setValue(model.getEmail());
            batch.add(new User(model.getName(), model.getAge(), emailAddress));
        }
        return reactiveMongoTemplate.insert(batch, User.class);
//        return reactiveMongoTemplate.inTransaction().execute(
//                action -> action.insert(batch.get(0)).then(action.insert(batch.get(1)))
//        );
    }
}
