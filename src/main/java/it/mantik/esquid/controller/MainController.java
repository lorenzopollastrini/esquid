package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.mantik.esquid.model.Competition;
import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.GoogleUserInfo;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.CompetitionService;
import it.mantik.esquid.service.CredentialsService;
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
	
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal OidcUser oidcUser, Model model) {
		
		User currentUser;
		if (oidcUser != null) {
			GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
	        
	        currentUser = userService.findByoAuthUniqueIdentifier(googleUserInfo.getId());
		} else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
			currentUser = credentials.getUser();
		}
		
		Collection<User> pendingUsers = userService.findByEnabled(false);
		Collection<User> members = userService.findByEnabled(true);
		Collection<Event> events = eventService.findAll();
		Collection<Competition> competitions = competitionService.findAll();
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("pendingUsers", pendingUsers);
		model.addAttribute("members", members);
		model.addAttribute("events", events);
		model.addAttribute("competitions", competitions);
		
		return "home";
		
	}
	
}
