package it.mantik.esquid.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getCredentials().getRole());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("Credenziali errate");
		}
	}
	
	private List<GrantedAuthority> getUserAuthority(String userRole) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        grantedAuthorities.add(new SimpleGrantedAuthority(userRole));
        
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getCredentials().getUsername(),
        		user.getCredentials().getPassword(),
        		user.isEnabled(),
        		true,
        		true,
        		true,
        		authorities);
    }

}
