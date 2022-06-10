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
	
	
	
}
