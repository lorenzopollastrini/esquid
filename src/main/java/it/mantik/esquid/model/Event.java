package it.mantik.esquid.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dateTime;
	
	@ManyToMany
	private Collection<User> participants;
	
	public Event() {
		participants = new ArrayList<>();
	}
	
	public void addParticipant(User participant) {
		participants.add(participant);
	}
	
	public void removeParticipant(User participant) {
		participants.remove(participant);
	}
	
}
