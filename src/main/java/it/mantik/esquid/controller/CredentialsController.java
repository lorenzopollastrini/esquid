package it.mantik.esquid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.service.CredentialsService;

@Controller
public class CredentialsController {

	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/admin/credentials/{credentialsId}/enable")
	public String enableCredentials(@PathVariable("credentialsId") Long credentialsId) {
		
		Credentials credentials = credentialsService.findById(credentialsId);
		
		credentials.setEnabled(true);
		
		credentialsService.save(credentials);
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/credentials/{credentialsId}/disable")
	public String disableCredentials(@PathVariable("credentialsId") Long credentialsId) {
		
		Credentials credentials = credentialsService.findById(credentialsId);
		
		credentials.setEnabled(false);
		
		credentialsService.save(credentials);
		
		/* TODO: remove the associated user from events etc.;
		 * logout the associated user;
		 */
		
		return "redirect:/admin";
		
	}
	
}
