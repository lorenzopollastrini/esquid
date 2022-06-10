package it.mantik.esquid.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User findByoAuthUniqueIdentifier(String oAuthUniqueIdentifier) {
		Optional<User> user = userRepository.findByoAuthUniqueIdentifier(oAuthUniqueIdentifier);
		
		if (user.isPresent()) {
			return user.get();
		}
		
		return null;
	}
	
	public Collection<User> findByEnabled(boolean enabled) {
		return userRepository.findByEnabled(enabled);
	}
	
}
