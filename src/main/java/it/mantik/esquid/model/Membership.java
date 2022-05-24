package it.mantik.esquid.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@ElementCollection(targetClass = UserRole.class)
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;
	
	public Membership() {
		
	}
	
	public Membership(User user, Team team) {
		this.user = user;
		this.team = team;
		roles = new HashSet<>();
	}
	
	public Membership(User user, Team team, UserRole role) {
		this.user = user;
		this.team = team;
		roles = new HashSet<>(Arrays.asList(role));
	}
	
	public void addRole(UserRole role) {
		roles.add(role);
	}

}
