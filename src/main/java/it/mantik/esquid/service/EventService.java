package it.mantik.esquid.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public Collection<Event> findAll() {
		
		Collection<Event> result = new ArrayList<>();
		
		eventRepository.findAll().forEach(result::add);
		
		return result;
		
	}
	
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
	}

}
