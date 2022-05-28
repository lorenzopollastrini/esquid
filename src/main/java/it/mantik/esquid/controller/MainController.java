package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.service.EventService;

@Controller
public class MainController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		Collection<Event> events = eventService.findAll();
		
		model.addAttribute("events", events);
		
		return "home";
		
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		
		Collection<Event> events = eventService.findAll();
		
		model.addAttribute("events", events);
		
		return "admin-home";
		
	}

}
