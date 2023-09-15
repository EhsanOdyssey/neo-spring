package neo.ehsanodyssey.java.spring.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import neo.ehsanodyssey.java.spring.entities.Resource;
import neo.ehsanodyssey.java.spring.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@Autowired
	private ResourceService service;
	
	@RequestMapping("/{resourceId}")
	@ResponseBody
	public Resource findResource(@PathVariable("resourceId") Resource resource) {
		return resource;
	}
		
	@RequestMapping("/find")
	public String find(Model model){
		model.addAttribute("resources", service.findAll());
		return "resources";
	}
}
