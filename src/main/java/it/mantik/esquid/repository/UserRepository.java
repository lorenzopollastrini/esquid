package it.mantik.esquid.repository;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
