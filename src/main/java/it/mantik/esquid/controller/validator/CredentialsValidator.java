package it.mantik.esquid.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Credentials.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Credentials credentials = (Credentials) target;
		String username = credentials.getUsername();
		
		if (credentialsService.findByUsername(username) != null){
			errors.rejectValue("username", "duplicate");
		}
		
	}

}
