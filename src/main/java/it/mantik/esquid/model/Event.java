package it.mantik.esquid.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Team team;
	
	private String name;
	
	private LocalDateTime dateTime;
	
	@ManyToMany
	private Collection<User> participants;
	
	public Event() {
		
	}
	
}
