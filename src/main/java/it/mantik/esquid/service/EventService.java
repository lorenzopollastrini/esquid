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
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public Collection<Event> findAllByOrderByDateTimeAsc() {
		
		Collection<Event> result = new ArrayList<>();
		
		eventRepository.findAllByOrderByDateTimeAsc().forEach(result::add);
		
		return result;
		
	}
	
	public Event findById(Long id) {
		return eventRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
	}

}
