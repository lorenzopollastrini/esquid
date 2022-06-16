package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Competition;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

	public Collection<Competition> findAllByOrderByStartDateTimeAsc();
	
}
