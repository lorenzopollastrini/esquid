package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.mantik.esquid.model.Competition;
import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.CompetitionService;
import it.mantik.esquid.service.EventService;
import it.mantik.esquid.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CompetitionService competitionService;
	
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal OidcUser oidcUser, Model model) {
		
		User currentUser = userService.getCurrentUser(oidcUser);
		
		Collection<User> pendingUsers = userService.findByEnabled(false);
		Collection<User> members = userService.findByEnabled(true);
		Collection<Event> events = eventService.findAllByOrderByDateTimeAsc();
		Collection<Competition> competitions = competitionService.findAllByOrderByStartDateTimeAsc();
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("pendingUsers", pendingUsers);
		model.addAttribute("members", members);
		model.addAttribute("events", events);
		model.addAttribute("competitions", competitions);
		
		return "home";
		
	}
	
}
