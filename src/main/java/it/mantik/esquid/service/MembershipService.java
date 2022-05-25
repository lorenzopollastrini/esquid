package it.mantik.esquid.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Membership;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.MembershipRepository;

@Service
public class MembershipService {

	@Autowired
	MembershipRepository membershipRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TeamService teamService;
	
	public Membership create(User user, Team team) {
		return save(new Membership(user, team));
	}
	
	public Membership create(User user, Team team, Boolean isOwnership) {
		return save(new Membership(user, team, isOwnership));
	}
	
	@Transactional
	public Membership save(Membership membership) {
		return membershipRepository.save(membership);
	}
	
	public Membership findById(Long id) {
		return membershipRepository.findById(id).get();
	}
	
	public Collection<Membership> findByUser(User user) {
		return membershipRepository.findByUser(user);
	}
	
	public Collection<Membership> findByTeam(Team team) {
		return membershipRepository.findByTeam(team);
	}
	
	public void makeOwnership(Long membershipId) {
		
		findById(membershipId).makeOwnership();
		
	}
	
}
