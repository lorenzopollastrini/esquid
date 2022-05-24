package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Membership;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;

public interface MembershipRepository extends CrudRepository<Membership, Long> {

	public Collection<Membership> findByUser(User user);
	
	public Collection<Membership> findByTeam(Team team);
	
}
