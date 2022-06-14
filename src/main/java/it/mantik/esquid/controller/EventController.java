package it.mantik.esquid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.EventService;
import it.mantik.esquid.service.UserService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/admin/event/new")
	public String getCreateEventView(Model model) {
		
		model.addAttribute("event", new Event());
		
		return "create-event";
		
	}
	
	@PostMapping("/admin/event")
	public String createEvent(@Valid @ModelAttribute("event") Event event,
			BindingResult eventBindingResult) {
		
		if (!eventBindingResult.hasErrors()) {
			Event savedEvent = eventService.save(event);
			return "redirect:/event/" + savedEvent.getId();
		}
		
		return "create-event";
		
	}
	
	@GetMapping("/event/{eventId}")
	public String getEvent(@AuthenticationPrincipal OidcUser oidcUser,
			@PathVariable("eventId") Long eventId,
			Model model) {
		
		User currentUser = userService.getCurrentUser(oidcUser);
		Event event = eventService.findById(eventId);
		
		model.addAttribute("currentUser", currentUser);
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
			BindingResult eventBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (!eventBindingResult.hasErrors()) {
			eventService.save(event);
			redirectAttributes.addFlashAttribute("successFlashMessages", "Evento modificato con successo");
			return "redirect:/event/" + event.getId();
		}
		
		return "update-event";
		
	}
	
	@GetMapping("/admin/event/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId") Long eventId,
			RedirectAttributes redirectAttributes) {
		
		eventService.deleteById(eventId);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Evento annullato con successo");
		
		return "redirect:/";
		
	}
	
	@GetMapping("/event/{eventId}/participate")
	public String participateToEvent(@AuthenticationPrincipal OidcUser oidcUser,
			@PathVariable("eventId") Long eventId,
			RedirectAttributes redirectAttributes) {
		
		User currentUser = userService.getCurrentUser(oidcUser);
		
		Event event = eventService.findById(eventId);
		
		event.addParticipant(currentUser);
		
		eventService.save(event);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Partecipazione confermata");
		
		return "redirect:/event/" + eventId;
		
	}
	
	@GetMapping("/event/{eventId}/cancel-participation")
	public String cancelParticipationToEvent(@AuthenticationPrincipal OidcUser oidcUser,
			@PathVariable("eventId") Long eventId,
			RedirectAttributes redirectAttributes) {
		
		User currentUser = userService.getCurrentUser(oidcUser);
		
		Event event = eventService.findById(eventId);
		
		event.removeParticipant(currentUser);
		
		eventService.save(event);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Partecipazione annullata");
		
		return "redirect:/";
		
	}
	
}
