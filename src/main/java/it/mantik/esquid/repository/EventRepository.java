package it.mantik.esquid.repository;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
