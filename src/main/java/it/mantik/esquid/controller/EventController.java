package it.mantik.esquid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.model.Event;
import it.mantik.esquid.service.CredentialsService;
import it.mantik.esquid.service.EventService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CredentialsService credentialsService;

	@GetMapping("/admin/event/new")
	public String getCreateEventView(Model model) {
		
		model.addAttribute("event", new Event());
		
		return "create-event";
		
	}
	
	@PostMapping("/admin/event")
	public String createEvent(@Valid @ModelAttribute("event") Event event,
			BindingResult eventBindingResult) {
		
		if (!eventBindingResult.hasErrors()) {
			eventService.save(event);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/event/{eventId}")
	public String getEvent(@PathVariable("eventId") Long eventId,
			Model model) {
		
		Event event = eventService.findById(eventId);
		
		model.addAttribute("event", event);
		
		return "event";
		
	}
	
	@GetMapping("/admin/event/{eventId}/update")
	public String getUpdateEventView(@PathVariable("eventId") Long eventId,
			Model model) {
		
		model.addAttribute("event", eventService.findById(eventId));
		
		return "update-event";
		
	}
	
	@PostMapping("/admin/event/{eventId}/update")
	public String updateEvent(@Valid @ModelAttribute("event") Event event,
			BindingResult eventBindingResult) {
		
		if (!eventBindingResult.hasErrors()) {
			eventService.save(event);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/event/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId") Long eventId) {
		
		eventService.deleteById(eventId);
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/event/{eventId}/participate")
	public String participateToEvent(@PathVariable("eventId") Long eventId) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		
		Event event = eventService.findById(eventId);
		
		event.addParticipant(credentials.getUser());
		
		eventService.save(event);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/event/{eventId}/cancel-participation")
	public String cancelParticipationToEvent(@PathVariable("eventId") Long eventId) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		
		Event event = eventService.findById(eventId);
		
		event.removeParticipant(credentials.getUser());
		
		eventService.save(event);
		
		return "redirect:/";
		
	}
	
}
