package neo.ehsanodyssey.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/home2")
	public String home2() {
		return "home2";
	}

	@RequestMapping("/home3")
	public String home3() {
		return "home3";
	}
	
}
