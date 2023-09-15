package neo.ehsanodyssey.java.spring.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import neo.ehsanodyssey.java.spring.entities.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("resource", new Resource());

		List<String> options = new LinkedList<>(
				Arrays.asList(new String[] { "Material", "Staff", "Other", "Equipment" }));
		model.addAttribute("typeOptions", options);

		List<String> radios = new LinkedList<>(Arrays.asList(new String[] { "Hours", "Piece", "Tons" }));
		model.addAttribute("radioOptions", radios);

		List<String> checks = new LinkedList<>(
				Arrays.asList(new String[] { "Lead Time", "Special Rate", "Requires Approval" }));
		
		model.addAttribute("checkOptions", checks);

		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println(resource);
		return "redirect:/resource/add";
	}
}
