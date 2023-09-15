package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.model.EmailAddress;
import neo.ehsanodyssey.java.spring.model.User;
import neo.ehsanodyssey.java.spring.dto.UserModel;
import neo.ehsanodyssey.java.spring.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public User insert(UserModel userModel) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setValue(userModel.getEmail());
        User user = new User(userModel.getName(), userModel.getAge(), emailAddress);
        return mongoTemplate.insert(user);
    }

    public Optional<User> findUsersByName(String name) {
        return userRepository.findUsersByName(name).stream().filter(u -> u.getName().equals(name)).findAny();
    }

    public Optional<User> findNameAndAgeExcludeId(String name) {
        return userRepository.findNameAndAgeExcludeId().stream().filter(u -> u.getName().equals(name)).findAny();
    }

    public User findUserByName(String name) {
//        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        Query query = Query.query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public Page<User> findAllUsersPagination(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }
}
