package it.mantik.esquid.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PlayedMatch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Competition competition;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime dateTime;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40)
	private String opponent;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private MatchResult result;
	
}
