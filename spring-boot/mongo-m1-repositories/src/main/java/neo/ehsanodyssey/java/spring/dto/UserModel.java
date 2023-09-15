package neo.ehsanodyssey.java.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserModel implements Serializable {

    private String name;
    private Integer age;
    private String email;
}
