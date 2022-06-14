package it.mantik.esquid.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.Credentials;
import it.mantik.esquid.model.GoogleUserInfo;
import it.mantik.esquid.model.User;
import it.mantik.esquid.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * @param oidcUser
	 * @return the current user, distinguishing between OAuth and local users
	 */
	public User getCurrentUser(OidcUser oidcUser) {
		User currentUser;
		if (oidcUser != null) {
			GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
	        
	        currentUser = findByoAuthUniqueIdentifier(googleUserInfo.getId());
		} else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
			currentUser = credentials.getUser();
		}
		return currentUser;
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
