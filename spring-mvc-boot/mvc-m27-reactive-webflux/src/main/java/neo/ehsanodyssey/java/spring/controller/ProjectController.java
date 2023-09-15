package neo.ehsanodyssey.java.spring.controller;

import java.time.Duration;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.entities.ProjectStatus;
import neo.ehsanodyssey.java.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	public ProjectService service;

	// Service with Reactive Flux type
	@ResponseBody
	@GetMapping(value="/{name}/events", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ProjectStatus> getProjectStatus(@PathVariable String name){
		return this.service.getStatus(name).delayElements(Duration.ofSeconds(1));
	}

	// Service with Reactive Mono type
	@ResponseBody
	@GetMapping(value="/{name}")
	public Mono<Project> getProject(@PathVariable String name){
		return this.service.findByName(name);
	}
}
