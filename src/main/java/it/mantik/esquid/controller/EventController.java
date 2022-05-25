package it.mantik.esquid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.EventService;
import it.mantik.esquid.service.TeamService;
import it.mantik.esquid.service.UserService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/team/{teamId}/event/{eventId}")
	public String getEvent(@PathVariable("eventId") Long eventId, Model model) {
		
		Event event = eventService.findById(eventId);
		
		model.addAttribute("team", event.getTeam());
		model.addAttribute("event", event);
		model.addAttribute("participants", event.getParticipants());
		
		return "event";
		
	}

	@GetMapping("/team/{teamId}/event/new")
	public String getCreateEventForm(@PathVariable("teamId") Long teamId, Model model) {
		
		Team team = teamService.findById(teamId);
		
		model.addAttribute("planner", team);
		model.addAttribute("event", new Event());
		
		return "create-event-form";
		
	}
	
	@PostMapping("/team/{teamId}/event")
	public String create(@PathVariable("teamId") Long teamId,
			@ModelAttribute("event") Event event) {
		
		event.setTeam(teamService.findById(teamId));
		
		eventService.save(event);
		
		return "redirect:/team/" + teamId;
		
	}
	
	@GetMapping("/team/{teamId}/event/{eventId}/cancel")
	public String cancel(@PathVariable("teamId") Long teamId,
			@PathVariable("eventId") Long eventId) {
		
		eventService.deleteById(eventId);
		
		return "redirect:/team/" + teamId;
		
	}
	
	@GetMapping("/team/{teamId}/event/{eventId}/participate")
	public String participate(@PathVariable("eventId") Long eventId) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		
		Event event = eventService.findById(eventId);
		eventService.addParticipant(event, currentUser);
		eventService.save(event);
		
		return "redirect:/team/" + event.getTeam().getId() + "/event/" + eventId;
		
	}
	
	@GetMapping("/team/{teamId}/event/{eventId}/cancel-participation")
	public String cancelParticipation(@PathVariable("eventId") Long eventId) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		
		Event event = eventService.findById(eventId);
		eventService.removeParticipant(event, currentUser);
		eventService.save(event);
		
		return "redirect:/team/" + event.getTeam().getId();
		
	}
	
}
