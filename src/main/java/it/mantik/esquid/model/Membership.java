package it.mantik.esquid.model;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Membership {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Member user;
	
	@ManyToOne
	private Team team;
	
	@ElementCollection(targetClass = UserRole.class)
	@Enumerated(EnumType.STRING)
	private Collection<UserRole> roles;

}
