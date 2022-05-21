package it.mantik.esquid.model;

import java.util.Collection;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Membership {
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Team team;
	
	@ManyToMany
	private Collection<UserRole> roles;

}
