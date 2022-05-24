package it.mantik.esquid.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.InviteRepository;

@Service
public class InviteService {
	
	@Autowired
	InviteRepository inviteRepository;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	public Invite create(Team sender, User recipient) {
		
		return save(new Invite(sender, recipient));
		
	}
	
	@Transactional
	public Invite save(Invite invite) {
		return inviteRepository.save(invite);
	}
	
	public Invite findById(Long id) {
		return inviteRepository.findById(id).get();
	}
	
	public Collection<Invite> findBySender(Team sender) {
		return inviteRepository.findBySender(sender);
	}
	
	public Collection<Invite> findBySenderId(Long senderId) {
		return inviteRepository.findBySender(teamService.findById(senderId));
	}
	
	public Collection<Invite> findByRecipient(User recipient) {
		return inviteRepository.findByRecipient(recipient);
	}
	
	public Collection<Invite> findByRecipientId(String recipientId) {
		return inviteRepository.findByRecipient(userService.findById(recipientId));
	}
	
	public void deleteById(Long id) {
		inviteRepository.deleteById(id);
	}

}
