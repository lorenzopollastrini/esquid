package it.mantik.esquid.controller.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.mantik.esquid.model.Competition;

@Component
public class CompetitionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Competition.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Competition competition = (Competition) target;
		LocalDateTime endDateTime = competition.getEndDateTime();
		LocalDateTime startDateTime = competition.getStartDateTime();
		
		if(endDateTime != null && startDateTime != null) {
			if(endDateTime.isBefore(startDateTime)) { 
				errors.rejectValue("endDateTime", "consistentDate");
			}	
		}
	
	}
	
}
