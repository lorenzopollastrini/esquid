package it.mantik.esquid.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Invite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Team sender;

	@ManyToOne
	private User recipient;
	
	private LocalDateTime dateTime;
	
	public Invite(Team sender, User recipient) {
		this.sender = sender;
		this.recipient = recipient;
		dateTime = LocalDateTime.now();
	}
	
}
