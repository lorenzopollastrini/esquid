package it.mantik.esquid.repository;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.User;

public interface MemberRepository extends CrudRepository<User, String> {

}
