package it.mantik.esquid.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Competition;
import it.mantik.esquid.repository.CompetitionRepository;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Transactional
	public Competition save(Competition competition) {
		return competitionRepository.save(competition);
	}
	
	public Collection<Competition> findAllByOrderByStartDateTimeAsc() {
		
		Collection<Competition> result = new ArrayList<>();
		
		competitionRepository.findAllByOrderByStartDateTimeAsc().forEach(result::add);
		
		return result;
		
	}
	
	public Competition findById(Long id) {
		return competitionRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		competitionRepository.deleteById(id);
	}
	
}
