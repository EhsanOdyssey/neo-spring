package neo.ehsanodyssey.java.spring.entities;

import org.springframework.stereotype.Component;

@Component
public class Persepolis implements Team {

    @Override
    public String getName() {
        return "Persepolis F.C.";
    }
}
