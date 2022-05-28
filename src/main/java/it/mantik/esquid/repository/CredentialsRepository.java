package it.mantik.esquid.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	public Optional<Credentials> findByUsername(String username);
		
}
