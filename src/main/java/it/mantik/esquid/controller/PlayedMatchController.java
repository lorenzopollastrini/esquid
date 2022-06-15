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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.mantik.esquid.model.Competition;
import it.mantik.esquid.model.PlayedMatch;
import it.mantik.esquid.service.CompetitionService;
import it.mantik.esquid.service.PlayedMatchService;

@Controller
public class PlayedMatchController {
	
	@Autowired
	private PlayedMatchService playedMatchService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping("/admin/competition/{competitionId}/played-match/new")
	public String getCreatePlayedMatchView(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		Competition competition = competitionService.findById(competitionId);
		
		model.addAttribute("playedMatch", new PlayedMatch());
		model.addAttribute("competition", competition);
		
		return "create-played-match";
		
	}
	
	@PostMapping("/admin/competition/{competitionId}/played-match")
	public String createPlayedMatch(@Valid @ModelAttribute("playedMatch") PlayedMatch playedMatch,
			BindingResult playedMatchBindingResult,
			@PathVariable("competitionId") Long competitionId,
			RedirectAttributes redirectAttributes,
			Model model) {
		
		Competition competition = competitionService.findById(competitionId);
		
		if (!playedMatchBindingResult.hasErrors()) {
			
			playedMatch.setCompetition(competition);
			
			PlayedMatch savedPlayedMatch = playedMatchService.save(playedMatch);
			
			competition.addPlayedMatch(savedPlayedMatch);
			competitionService.save(competition);
			
			redirectAttributes.addFlashAttribute("successFlashMessages", "Partita giocata aggiunta con successo");
			
			return "redirect:/competition/" + competitionId;
		}
		
		model.addAttribute("competition", competition);
		
		return "create-played-match";
		
	}
	
	@GetMapping("/admin/played-match/{playedMatchId}/update")
	public String getUpdatePlayedMatchView(@PathVariable("playedMatchId") Long playedMatchId,
			Model model) {
		
		model.addAttribute("playedMatch", playedMatchService.findById(playedMatchId));
		
		return "update-played-match";
		
	}
	
	@PostMapping("/admin/played-match/{playedMatchId}/update")
	public String updatePlayedMatch(@Valid @ModelAttribute("playedMatch") PlayedMatch playedMatch,
			BindingResult playedMatchBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (!playedMatchBindingResult.hasErrors()) {
			playedMatchService.save(playedMatch);
			redirectAttributes.addFlashAttribute("successFlashMessages", "Partita giocata modificata con successo");
			return "redirect:/competition/" + playedMatch.getCompetition().getId();
		}
		
		return "update-played-match";
		
	}
	
	@GetMapping("/admin/played-match/{playedMatchId}/delete")
	public String deletePlayedMatch(@PathVariable("playedMatchId") Long playedMatchId,
			RedirectAttributes redirectAttributes) {
		
		PlayedMatch playedMatch = playedMatchService.findById(playedMatchId);
		
		playedMatchService.delete(playedMatch);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Partita giocata cancellata con successo");
		
		return "redirect:/competition/" + playedMatch.getCompetition().getId();
		
	}

}
