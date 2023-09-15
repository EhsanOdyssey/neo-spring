package neo.ehsanodyssey.java.spring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
public class ActionModel implements Serializable {

    private String description;
    private String time;
}
