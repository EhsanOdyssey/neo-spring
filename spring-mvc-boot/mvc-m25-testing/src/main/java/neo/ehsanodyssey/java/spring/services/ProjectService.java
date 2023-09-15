package neo.ehsanodyssey.java.spring.services;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.entities.Sponsor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectService {

    private List<Project> projects = new LinkedList<>();

    public ProjectService() {
        Project javaProject = this.createProject("Java Project", "This is a Java Project");
        Project javascriptProject = this.createProject("Javascript Project", "This is a Javascript Project");
        Project htmlProject = this.createProject("HTML Project", "This is an HTML project");

        this.projects.addAll(Arrays.asList(new Project[]{javaProject, javascriptProject, htmlProject}));
    }

    public List<Project> findAll() {
        return this.projects;
    }

    public Project find(Long projectId) {
        return this.projects.stream().filter(p -> {
            return p.getProjectId().equals(projectId);
        }).collect(Collectors.toList()).get(0);
    }

    public Project findLast() {
        return this.projects.stream().max(Comparator.comparingLong(Project::getProjectId)).orElse(new Project());
    }

    public void save(Project project) {
        this.projects.add(project);
    }

    private Project createProject(String title, String description) {
        Sponsor sponsor = new Sponsor();
        sponsor.setName("Nasa");
        Project project = new Project();
        project.setName(title);
        project.setAuthorizedFunds(new BigDecimal("100000"));
        project.setAuthorizedHours(new BigDecimal("1000"));
        project.setProjectId(1L);
        project.setSpecial(false);
        project.setType("multi");
        project.setYear("2015");
        project.setDescription(description);
        project.setSponsor(sponsor);
        return project;
    }
}