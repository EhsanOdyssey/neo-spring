package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.beans.HitCounter;
import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    public ProjectService projectService;

    @Autowired
    public HitCounter hitCounter;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProject(Model model) {

        hitCounter.setHits(hitCounter.getHits() + 1);
        System.out.println("The number of hits is: " + hitCounter.getHits());

        model.addAttribute("types", new ArrayList() {{
            add("");
            add("Single Year");
            add("Multi Year");
        }});

        model.addAttribute("project", new Project());

        return "project_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveProject(@Valid @ModelAttribute Project project, Errors errors, RedirectAttributes attributes) {
        System.out.println("invoking save project");

        if (errors.hasErrors()) {
            System.out.println("The project did not validate.");
            return "project_add";
        }
        System.out.println("The project validated.");
        project.setProjectId(55L);
        this.projectService.save(project);

        attributes.addFlashAttribute("project", project);
        return "redirect:/home/";
    }
}
