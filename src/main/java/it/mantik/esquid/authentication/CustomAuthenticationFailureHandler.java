package it.mantik.esquid.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if (exception instanceof DisabledException) {
			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception.getMessage());
		} else {
			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception.getMessage());
		}
		
		
		response.sendRedirect("/login?error");
		
	}
}	