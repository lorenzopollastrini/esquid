package it.mantik.esquid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.service.EventService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;

	@GetMapping("/admin/create-event")
	public String getCreateEventView(Model model) {
		
		model.addAttribute("event", new Event());
		
		return "create-event";
		
	}
	
	@PostMapping("/admin/event")
	public String createEvent(@Valid @ModelAttribute("event") Event event,
			BindingResult eventBindingResult) {
		
		if (!eventBindingResult.hasErrors()) {
			eventService.saveEvent(event);
		}
		
		return "redirect:/admin";
		
	}
	
}
