package it.mantik.esquid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Member;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.service.InviteService;
import it.mantik.esquid.service.MemberService;
import it.mantik.esquid.service.TeamService;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private InviteService inviteService;
	
	@GetMapping("/create-team")
	public String getCreateTeamForm(Model model) {
		
		model.addAttribute("team", new Team());
		
		return "create-team-form";
		
	}
	
	@PostMapping("/create-team")
	public String createTeam(@ModelAttribute("team") Team team,
			BindingResult bindingResult, Model model) {
		
		if (!bindingResult.hasErrors()) {
			
			System.out.println(team.getName());
			teamService.save(team);
			
		}
		
		return "index";
		
	}
	
	@GetMapping("/team/{teamId}")
	public String getTeam(@PathVariable("teamId") Long teamId, Model model) {
		
		Team team = teamService.findById(teamId);
		
		model.addAttribute("team", team);
		
		return "team";
		
	}
	
	@GetMapping("/team/{teamId}/invite")
	public String getInviteForm(@PathVariable("teamId") Long teamId, Model model) {
		
		Team team = teamService.findById(teamId);
		
		model.addAttribute("team", team);
		
		return "invite-form";
		
	}
	
	@PostMapping("/team/{teamId}/invite")
	public String invite(@PathVariable("teamId") Long teamId, 
			@RequestParam("username") String username) {
		
		Team sender = teamService.findById(teamId);
		
		Member recipient = memberService.findById(username);
		
		Invite invite = new Invite(sender, recipient);
		
		inviteService.save(invite);
		
		sender.getInvites().add(invite);
		recipient.getInvites().add(invite);
		
		return "redirect:/";
		
	}

}
