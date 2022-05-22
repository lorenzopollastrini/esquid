package it.mantik.esquid.repository;

import org.springframework.data.repository.CrudRepository;

import it.mantik.esquid.model.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
