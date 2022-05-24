package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Invite;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;

public interface InviteRepository extends CrudRepository<Invite, Long> {
	
	public Collection<Invite> findBySender(Team sender);
	
	public Collection<Invite> findByRecipient(User recipient);

}
