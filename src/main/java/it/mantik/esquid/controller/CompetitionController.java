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

import it.mantik.esquid.controller.validator.CompetitionValidator;
import it.mantik.esquid.model.Competition;
import it.mantik.esquid.service.CompetitionService;

@Controller
public class CompetitionController {
	
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private CompetitionValidator competitionValidator;
	
	@GetMapping("/admin/competition/new")
	public String getCreateCompetitionView(Model model) {
		
		model.addAttribute("competition", new Competition());
		
		return "create-competition";
		
	}
	
	@PostMapping("/admin/competition")
	public String createCompetition(@Valid @ModelAttribute("competition") Competition competition,
			BindingResult competitionBindingResult) {
		
		competitionValidator.validate(competition, competitionBindingResult);
		
		if (!competitionBindingResult.hasErrors()) {
			Competition savedCompetition = competitionService.save(competition);
			return "redirect:/competition/" + savedCompetition.getId();
		}
		
		return "create-competition";
		
	}
	
	@GetMapping("/competition/{competitionId}")
	public String getCompetition(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		Competition competition = competitionService.findById(competitionId);
		
		model.addAttribute("competition", competition);
		
		return "competition";
		
	}
	
	@GetMapping("/admin/competition/{competitionId}/update")
	public String getUpdateCompetitionView(@PathVariable("competitionId") Long competitionId,
			Model model) {
		
		model.addAttribute("competition", competitionService.findById(competitionId));
		
		return "update-competition";
		
	}
	
	@PostMapping("/admin/competition/{competitionId}/update")
	public String updateCompetition(@Valid @ModelAttribute("competition") Competition competition,
			BindingResult competitionBindingResult,
			RedirectAttributes redirectAttributes) {
		
		competitionValidator.validate(competition, competitionBindingResult);
		
		if (!competitionBindingResult.hasErrors()) {
			competitionService.save(competition);
			redirectAttributes.addFlashAttribute("successFlashMessages", "Competizione modificata con successo");
			return "redirect:/competition/" + competition.getId();
		}
		
		return "update-competition";
		
	}
	
	@GetMapping("/admin/competition/{competitionId}/delete")
	public String deleteCompetition(@PathVariable("competitionId") Long competitionId,
			RedirectAttributes redirectAttributes) {
		
		competitionService.deleteById(competitionId);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Competizione cancellata con successo");
		
		return "redirect:/";
		
	}

}
