package it.mantik.esquid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.model.UserRole;
import it.mantik.esquid.service.InviteService;
import it.mantik.esquid.service.MembershipService;
import it.mantik.esquid.service.UserService;

@Controller
public class InviteController {
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MembershipService membershipService;

	@GetMapping("/invite/{inviteId}/accept")
	public String accept(@PathVariable("inviteId") Long inviteId) {
		
		UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.findById(currentUserDetails.getUsername());
		
		Invite invite = inviteService.findById(inviteId);
		
		Team team = invite.getSender();
		
		membershipService.create(currentUser, team, UserRole.PLAYER);
		
		inviteService.deleteById(inviteId);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/invite/{inviteId}/decline")
	public String decline(@PathVariable("inviteId") Long inviteId) {
		
		inviteService.deleteById(inviteId);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/invite/{inviteId}/revoke")
	public String revoke(@PathVariable("inviteId") Long inviteId) {
		
		Invite invite = inviteService.findById(inviteId);
		Team sender = invite.getSender();
		
		inviteService.delete(invite);
		
		return "redirect:/teams/" + sender.getId();
		
	}
	
}
