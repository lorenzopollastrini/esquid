package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.Team;

public interface EventRepository extends CrudRepository<Event, Long> {

	public Collection<Event> findByTeam(Team team);
	
}
