package it.mantik.esquid.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Membership;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.InviteService;
import it.mantik.esquid.service.MembershipService;
import it.mantik.esquid.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private MembershipService membershipService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		Collection<Invite> invites = inviteService.findByRecipient(currentUser);
		Collection<Membership> memberships = membershipService.findByUser(currentUser);
		
		
		model.addAttribute("user", currentUser);
		model.addAttribute("invites", invites);
		model.addAttribute("memberships", memberships);
		
		return "index";
		
	}
	
}
