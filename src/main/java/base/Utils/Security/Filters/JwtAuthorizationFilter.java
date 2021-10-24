package base.Utils.Security.Filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import base.Utils.Security.JwtAuthenticationToken;
import io.jsonwebtoken.JwtException;


@Component
//@Order(1)
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	public JwtAuthorizationFilter(@Lazy AuthenticationManager authenticationManagerBean,@Lazy AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManagerBean,authenticationEntryPoint);
	}
	
	public JwtAuthenticationToken parseToken(HttpServletRequest request) throws JwtException{
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
			
		if (token!=null && !token.isBlank() && token.startsWith("Bearer ")) {
			return new JwtAuthenticationToken(token.replace("Bearer ", ""));
		}
		return new JwtAuthenticationToken(null);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		try {
			onSuccessfulAuthentication(
					request,
					response, 
					this.getAuthenticationManager().authenticate(parseToken(request))
					);
			chain.doFilter(request, response);
			
		} catch (AuthenticationException e) {
			this.getAuthenticationEntryPoint().commence(request, response, e);
			this.onUnsuccessfulAuthentication(request, response, e);
			return;
		} catch (NullPointerException e) {
			SecurityContextHolder.clearContext();
			chain.doFilter(request, response); 
		}
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {
		SecurityContextHolder.clearContext();
	}
}
