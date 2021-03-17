package base.Utils.Security.Filters;

import java.io.IOException;
import java.security.InvalidKeyException;

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
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import base.DTO.MyUser;
import base.Utils.Jwt.IJWTUtil;


@Component
@Order(0)
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final IJWTUtil tokenUtil;
	
	private Logger logger=LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	public JwtAuthenticationFilter(@Lazy AuthenticationManager authenticationManagerBean, IJWTUtil tokenUtil) {
	        this.setAuthenticationManager(authenticationManagerBean);
	        this.tokenUtil=tokenUtil;
	        setFilterProcessesUrl("/login");
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
			String token= tokenUtil.generateToken(user);
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
