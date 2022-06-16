package it.mantik.esquid.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.mantik.esquid.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.credentials.username = :username ORDER BY u.surname ASC")
	public Optional<User> findByUsername(@Param("username") String username);
	
	public Optional<User> findByoAuthUniqueIdentifier(String oAuthUniqueIdentifier);
	
	@Query("SELECT u FROM User u WHERE u.enabled = :enabled ORDER BY u.surname ASC")
	public Collection<User> findByEnabled(@Param("enabled") boolean enabled);
	
}
