package it.mantik.esquid.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.mantik.esquid.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	@Query("SELECT u FROM User u WHERE u.credentials.enabled = :enabled")
	public Collection<User> findByEnabled(@Param("enabled") boolean enabled);
	
}
