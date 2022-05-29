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
import it.mantik.esquid.service.CompetitionService;

@Controller
public class CompetitionController {
	
	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping("/admin/create-competition")
	public String getCreateCompetitionView(Model model) {
		
		model.addAttribute("competition", new Competition());
		
		return "create-competition";
		
	}
	
	@PostMapping("/admin/competition")
	public String createCompetition(@Valid @ModelAttribute("competition") Competition competition,
			BindingResult competitionBindingResult) {
		
		if (!competitionBindingResult.hasErrors()) {
			competitionService.save(competition);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/competition/{competitionId}")
	public String getCompetition(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		Competition competition = competitionService.findById(competitionId);
		
		model.addAttribute("competition", competition);
		
		return "competition";
		
	}
	
	@GetMapping("/admin/competition/{competitionId}")
	public String getAdminCompetition(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		Competition competition = competitionService.findById(competitionId);
		
		model.addAttribute("competition", competition);
		
		return "admin-competition";
		
	}
	
	@GetMapping("/admin/competition/{competitionId}/update")
	public String getUpdateCompetitionView(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		model.addAttribute("competition", competitionService.findById(competitionId));
		
		return "update-competition";
		
	}
	
	@PostMapping("/admin/competition/{competitionId}/update")
	public String updateCompetition(@Valid @ModelAttribute("competition") Competition competition,
			BindingResult competitionBindingResult) {
		
		if (!competitionBindingResult.hasErrors()) {
			competitionService.save(competition);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/competition/{competitionId}/delete")
	public String deleteCompetition(@PathVariable("competitionId") Long competitionId) {
		
		competitionService.deleteById(competitionId);
		
		return "redirect:/admin";
		
	}

}
