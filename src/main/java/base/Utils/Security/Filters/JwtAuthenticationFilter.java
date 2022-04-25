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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.DTO.MyUser;
import base.Utils.Jwt.IJWTUtil;
import base.Utils.Security.JwtAuthenticationToken;
import io.jsonwebtoken.JwtException;

@Component
//@Order(0)
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final IJWTUtil tokenUtil;
	
	private Logger logger=LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public JwtAuthenticationFilter(@Lazy AuthenticationManager authenticationManagerBean, IJWTUtil tokenUtil) {			
	        setAuthenticationManager(authenticationManagerBean);
	        this.tokenUtil=tokenUtil;
	        setFilterProcessesUrl("/login");
	        
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
	
	public JwtAuthenticationToken parseToken(HttpServletRequest request) throws JwtException{
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token!=null && !token.isBlank() && token.startsWith("Bearer ")) {
			return new JwtAuthenticationToken(token.replace("Bearer ", ""));
		}
		
		return new JwtAuthenticationToken(null);
	}
	
	
	@Override
	protected void successfulAuthentication(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        FilterChain filterChain,
	        Authentication authentication
	        ) 
	        		throws IOException,
	        		ServletException {
		MyUser<?,?> user = (MyUser<?,?>)authentication.getPrincipal();
		
		try {
			String token;
		token= tokenUtil.generateToken(user);
			response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+token);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		    response.setCharacterEncoding("UTF-8");
			response.flushBuffer();
		} catch (InvalidKeyException e) {
			response.sendError(500);
			response.flushBuffer();
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}	 
}
