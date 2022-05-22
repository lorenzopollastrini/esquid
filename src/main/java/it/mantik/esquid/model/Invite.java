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
	private Member recipient;
	
	private LocalDateTime dateTime;
	
	public Invite(Team sender, Member recipient) {
		this.sender = sender;
		this.recipient = recipient;
		dateTime = LocalDateTime.now();
	}
	
}
