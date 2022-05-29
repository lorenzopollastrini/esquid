package it.mantik.esquid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Match;
import it.mantik.esquid.repository.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Transactional
	public Match save(Match match) {
		return matchRepository.save(match);
	}
	
}
