package it.mantik.esquid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import it.mantik.esquid.model.GoogleUserInfo;
import it.mantik.esquid.model.User;

@Service
public class CustomOidcUserService extends OidcUserService {

	@Autowired
	private UserService userService;
	
	@Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        
        User user = userService.findByoAuthUniqueIdentifier(googleUserInfo.getId());
        
        if(user != null) {
        	if (!user.isEnabled()) {
            	throw new OAuth2AuthenticationException("Utente non abilitato.");
            } else {
            	return oidcUser;
            }
        } else {
        	try {
                processOidcUser(userRequest, oidcUser);
                throw new OAuth2AuthenticationException("Utente non abilitato.");
           } catch (Exception ex) {
               throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
           }
        }

    }
	
	private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        
        User user = new User(googleUserInfo.getId(), googleUserInfo.getGivenName(),
        		googleUserInfo.getFamilyName(), false);

        userService.save(user);

        return oidcUser;
    }

}
