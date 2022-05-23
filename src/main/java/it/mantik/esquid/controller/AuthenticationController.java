package it.mantik.esquid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.model.User;
import it.mantik.esquid.service.CredentialsService;

@Controller
public class AuthenticationController {

	@Autowired
	CredentialsService credentialsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		
		return "register";
		
	}

	@GetMapping("/login")
	public String getLoginForm(Model model) {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "login";
	}
	
	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {
		return "redirect:/login";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user,
			BindingResult userBindingResult,
			@Valid @ModelAttribute("credentials") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {
		
		user.setAlias(credentials.getUsername());
		
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			credentials.setUser(user);
			credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
			credentialsService.saveCredentials(credentials);
			return "registration-successful";
		}
		
		return "register";
		
	}
	
}
