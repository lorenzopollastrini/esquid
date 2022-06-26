package it.mantik.esquid.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40)
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future
	@NotNull
	private LocalDateTime dateTime;
	
	@ManyToMany
	@OrderBy("surname")
	private Set<User> participants;
	
	public Event() {
		participants = new HashSet<>();
	}
	
	public void addParticipant(User participant) {
		participants.add(participant);
	}
	
	public void removeParticipant(User participant) {
		participants.remove(participant);
	}
	
}
