package it.mantik.esquid.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "teams")
	private Set<Member> members;
	
	@OneToMany(mappedBy = "team")
	private List<Event> events;
	
	@OneToMany(mappedBy = "sender")
	private Set<Invite> invites;
	
	public Team() {
		members = new HashSet<>();
		events = new ArrayList<>();
		invites = new HashSet<>();
	}
	
}
