package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	public Collection<Event> findAllByOrderByDateTimeAsc();
	
}
