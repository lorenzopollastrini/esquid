package it.mantik.esquid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void save(User member) {
		memberRepository.save(member);
	}
	
	public User findById(String username) {
		return memberRepository.findById(username).get();
	}

}
