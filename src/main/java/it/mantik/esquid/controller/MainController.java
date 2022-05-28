package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.model.Event;
import it.mantik.esquid.service.CredentialsService;
import it.mantik.esquid.service.EventService;

@Controller
public class MainController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		
		Collection<Event> events = eventService.findAll();
		
		model.addAttribute("currentUser", credentials.getUser());
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
