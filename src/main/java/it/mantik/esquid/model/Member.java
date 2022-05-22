package it.mantik.esquid.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	private String username;
	
	private String name;
	
	private String surname;
	
	@ManyToMany
	private Set<Team> teams;
	
	@OneToMany(mappedBy = "recipient")
	private Set<Invite> invites;
	
	public Member() {
		teams = new HashSet<>();
		invites = new HashSet<>();
	}
}
