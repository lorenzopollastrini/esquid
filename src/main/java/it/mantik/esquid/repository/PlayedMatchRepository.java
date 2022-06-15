package it.mantik.esquid.repository;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.PlayedMatch;

public interface PlayedMatchRepository extends CrudRepository<PlayedMatch, Long> {

}
