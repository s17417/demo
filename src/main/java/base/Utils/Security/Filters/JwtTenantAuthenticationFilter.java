package base.Utils.Security.Filters;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.DTO.MyUser;
import base.Model.baza.Role;
import base.Utils.Jwt.IJWTUtil;
import base.Utils.Security.JwtAuthenticationToken;
import base.Utils.Security.JwtTenantAuthenticationToken;
import io.jsonwebtoken.JwtException;

@Component
public class JwtTenantAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private IJWTUtil tokenUtil;
	private Boolean postOnly=true;
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final AntPathRequestMatcher defaultAuthenticationPath = new AntPathRequestMatcher("/loginTenant",
			"POST");

	
	@Autowired
	public JwtTenantAuthenticationFilter(@Lazy AuthenticationManager authenticationManagerBean, IJWTUtil tokenUtil) {		
		super(defaultAuthenticationPath ,authenticationManagerBean);
		this.tokenUtil=tokenUtil;
		
		 setAuthenticationFailureHandler( (request, response, exception) -> {
	        	final Map<String, Object> body = new LinkedHashMap<>();
	        	response.setContentType("application/json");
			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    body.put("timestamp", Date.from(Instant.now()));
			    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
			    body.put("error", "Unauthorized-1");  
		        body.put("message", exception.getMessage());
		        body.put("path", request.getServletPath());
		        response.getOutputStream()
		          .println(objectMapper.writeValueAsString(body));							
			});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return this.getAuthenticationManager().authenticate(parseTenant(request));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		var user = (MyUser<?,?>)authentication.getPrincipal();
		var jwtTenantAuthentication = (JwtTenantAuthenticationToken)authentication;
		
		
		try {
			String token = tokenUtil.generateToken(user,jwtTenantAuthentication.getTenant());
			response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+token);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		    response.setCharacterEncoding("UTF-8");
			response.flushBuffer();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
			
		//super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.unsuccessfulAuthentication(request, response, failed);
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
	
	public JwtTenantAuthenticationToken parseTenant(HttpServletRequest request) throws JwtException{
		String tenant = request.getParameter("tenant");
		if (tenant!=null) {
			return new JwtTenantAuthenticationToken(tenant);
		}
		
		return new JwtTenantAuthenticationToken(null);
	}
}
