package it.mantik.esquid.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Competition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40)
	private String name;
	
	@OneToMany(mappedBy = "competition", cascade = CascadeType.REMOVE)
	private Collection<Match> matches;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future
	@NotNull
	private LocalDateTime startDateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future
	@NotNull
	private LocalDateTime endDateTime;
	
	public Competition() {
		matches = new ArrayList<>();
	}
	
	public void addMatch(Match match) {
		matches.add(match);
	}
	
	public void removeMatch(Match match) {
		matches.remove(match);
	}
	
}
