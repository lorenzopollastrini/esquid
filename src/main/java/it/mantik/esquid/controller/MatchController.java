package it.mantik.esquid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.mantik.esquid.model.Competition;
import it.mantik.esquid.model.Match;
import it.mantik.esquid.service.CompetitionService;
import it.mantik.esquid.service.MatchService;

@Controller
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping("/admin/competition/{competitionId}/match/new")
	public String getCreateMatchView(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		model.addAttribute("match", new Match());
		model.addAttribute("competitionId", competitionId);
		
		return "create-match";
		
	}
	
	@PostMapping("/admin/competition/{competitionId}/match")
	public String createMatch(@Valid @ModelAttribute("match") Match match,
			BindingResult matchBindingResult,
			@PathVariable("competitionId") Long competitionId) {
		
		if (!matchBindingResult.hasErrors()) {
			
			Competition competition = competitionService.findById(competitionId);
			
			match.setCompetition(competition);
			
			Match savedMatch = matchService.save(match);
			
			competition.addMatch(savedMatch);
			competitionService.save(competition);
			
			return "redirect:/admin";
		}
		
		return "create-match";
		
	}
	
	@GetMapping("/admin/match/{matchId}/update")
	public String getUpdateMatchView(@PathVariable("matchId") Long matchId,
			Model model) {
		
		model.addAttribute("match", matchService.findById(matchId));
		
		return "update-match";
		
	}
	
	@PostMapping("/admin/match/{matchId}/update")
	public String updateMatch(@Valid @ModelAttribute("match") Match match,
			BindingResult matchBindingResult) {
		
		if (!matchBindingResult.hasErrors()) {
			matchService.save(match);
			return "redirect:/admin";
		}
		
		return "update-match";
		
	}
	
	@GetMapping("/admin/match/{matchId}/delete")
	public String deleteMatch(@PathVariable("matchId") Long matchId) {
		
		Match match = matchService.findById(matchId);
		Competition competition = match.getCompetition();
		
		competition.removeMatch(match);
		competitionService.save(competition);
		
		matchService.delete(match);
		
		return "redirect:/admin";
		
	}

}