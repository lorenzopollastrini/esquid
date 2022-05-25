package it.mantik.esquid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Membership {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Team team;
	
	private Boolean isOwnership;
	
	public Membership() {
		isOwnership = false;
	}
	
	public Membership(User user, Team team) {
		this.user = user;
		this.team = team;
		isOwnership = false;
	}
	
	public Membership(User user, Team team, Boolean isOwnership) {
		this.user = user;
		this.team = team;
		this.isOwnership = isOwnership;
	}
	
	public void makeOwnership() {
		isOwnership = true;
	}

}
