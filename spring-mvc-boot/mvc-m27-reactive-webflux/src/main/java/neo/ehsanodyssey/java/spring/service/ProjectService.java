package neo.ehsanodyssey.java.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.entities.ProjectStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();

    @PostConstruct
    public void populate() {
        projects = Stream.of("Software", "Hardware", "Network").map(name -> {
            return new Project(name, new Random().nextInt(1000));
        }).collect(Collectors.toList());
    }

    // Service method that returns Reactive Flux type
    public Flux<ProjectStatus> getStatus(String name) {
        String[] statusTypes = new String[]{"Started", "In Progress", "Completed"};
        return Flux.fromStream(Stream.generate(() -> new ProjectStatus(name, null))).doOnEach(s -> {
            s.get().setStatus(statusTypes[new Random().nextInt(3)]);
        });
    }

    // Service method that returns Reactive Mono type
    public Mono<Project> findByName(String name) {
        return Mono.just(projects.stream().filter(project -> {
            return name.equalsIgnoreCase(project.getName());
        }).findFirst().get());
    }
}
