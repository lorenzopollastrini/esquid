package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Membership;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.EventService;
import it.mantik.esquid.service.InviteService;
import it.mantik.esquid.service.MembershipService;
import it.mantik.esquid.service.TeamService;
import it.mantik.esquid.service.UserService;

@Controller
public class TeamController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/team/new")
	public String getCreateTeamForm(Model model) {
		
		model.addAttribute("team", new Team());
		
		return "create-team-form";
		
	}
	
	@PostMapping("/team")
	public String createTeam(@ModelAttribute("team") Team team) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		
		Team savedTeam = teamService.save(team);
		
		membershipService.create(currentUser, savedTeam, true);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/team/{teamId}")
	public String getTeam(@PathVariable("teamId") Long teamId, Model model) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		
		Team team = teamService.findById(teamId);
		Collection<Invite> invites = inviteService.findBySender(team);
		Collection<Membership> memberships = membershipService.findByTeam(team);
		Collection<Event> events = eventService.findByTeam(team);
		
		model.addAttribute("user", currentUser);
		model.addAttribute("team", team);
		model.addAttribute("invites", invites);
		model.addAttribute("memberships", memberships);
		model.addAttribute("events", events);
		
		return "team";
		
	}
	
	@PostMapping("/team/{teamId}/invite")
	public String invite(@PathVariable("teamId") Long teamId, 
			@RequestParam("username") String username) {
		
		Team sender = teamService.findById(teamId);
		
		User recipient = userService.findById(username);
		
		inviteService.create(sender, recipient);
		
		return "redirect:/team/" + teamId;
		
	}

}
