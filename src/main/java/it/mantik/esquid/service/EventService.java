package it.mantik.esquid.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Event;
import it.mantik.esquid.model.Team;
import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public Event findById(Long id) {
		return eventRepository.findById(id).get();
	}
	
	public Collection<Event> findByTeam(Team team) {
		return eventRepository.findByTeam(team);
	}
	
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
	}
	
	public void addParticipant(Event event, User participant) {
		event.addParticipant(participant);
	}
	
	public void removeParticipant(Event event, User participant) {
		event.removeParticipant(participant);
	}
	
}
