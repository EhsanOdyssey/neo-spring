package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.dto.ActionModel;
import neo.ehsanodyssey.java.spring.model.Action;
import neo.ehsanodyssey.java.spring.repository.ActionRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ActionService {

    private ActionRepository actionRepository;
    private MongoTemplate mongoTemplate;

    public ActionService(ActionRepository actionRepository, MongoTemplate mongoTemplate) {
        if (!mongoTemplate.collectionExists(Action.class)) {
            mongoTemplate.createCollection(Action.class);
        }
        this.actionRepository = actionRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Action insert(ActionModel model) {
        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = LocalDateTime.parse(model.getTime(),
                DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);
        Action action = new Action(model.getDescription(), zonedDateTime);
        return actionRepository.insert(action);
    }

    public Optional<Action> findActionById(String id) {
        return actionRepository.findById(id);
    }
}
