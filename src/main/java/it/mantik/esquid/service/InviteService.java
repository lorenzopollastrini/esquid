package it.mantik.esquid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.repository.InviteRepository;

@Service
public class InviteService {
	
	@Autowired
	InviteRepository inviteRepository;
	
	@Transactional
	public void save(Invite invite) {
		inviteRepository.save(invite);
	}

}
