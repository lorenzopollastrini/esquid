package it.mantik.esquid.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter
public class User {
	
	@Id
	private String alias;
	
	private String name;
	
	private String surname;
	
	@ManyToMany
	private Set<Team> teams;
	
	@OneToMany(mappedBy = "recipient")
	private Set<Invite> invites;
	
	public User() {
		teams = new HashSet<>();
		invites = new HashSet<>();
	}
}
