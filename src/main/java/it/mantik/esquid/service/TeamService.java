package it.mantik.esquid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Team;
import it.mantik.esquid.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Transactional
	public Team save(Team team) {
		return teamRepository.save(team);
	}
	
	public Team findById(Long id) {
		return teamRepository.findById(id).get();
	}

}
