package it.mantik.esquid.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials save(Credentials credentials) {
		return credentialsRepository.save(credentials);
	}
	
	public Credentials findById(Long id) {
		return credentialsRepository.findById(id).get();
	}
	
	public Credentials findByUsername(String username) {
		return credentialsRepository.findByUsername(username).get();
	}
	
}
