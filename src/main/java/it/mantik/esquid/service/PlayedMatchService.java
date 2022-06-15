package it.mantik.esquid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.PlayedMatch;
import it.mantik.esquid.repository.PlayedMatchRepository;

@Service
public class PlayedMatchService {

	@Autowired
	private PlayedMatchRepository playedMatchRepository;
	
	@Transactional
	public PlayedMatch save(PlayedMatch playedMatch) {
		return playedMatchRepository.save(playedMatch);
	}
	
	public PlayedMatch findById(Long id) {
		return playedMatchRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		playedMatchRepository.deleteById(id);
	}
	
	public void delete(PlayedMatch playedMatch) {
		playedMatchRepository.delete(playedMatch);
	}
	
}
