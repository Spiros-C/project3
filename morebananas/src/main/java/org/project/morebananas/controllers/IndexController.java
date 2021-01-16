package org.project.morebananas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

//	@RequestMapping("/")
//	String home(ModelMap modal) {
//		modal.addAttribute("title", "Index");
//		modal.addAttribute("message", "Test");
//		return "test";
//	}
        @RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
}




//https://www.tutorialspoint.com/hibernate/hibernate_criteria_queries.htm

