package base.Utils.Security;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import base.DTO.MyUser;
import base.Model.baza.Role;
import base.Services.baza.IUserDetailServiceTenant;
import base.Utils.Jwt.IJWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	private IJWTUtil tokenUtil;
	
	private IUserDetailServiceTenant userService;
	
	private Logger logger=LoggerFactory.getLogger(TokenAuthenticationProvider.class);
	
	protected TokenAuthenticationProvider(IJWTUtil tokenUtil, IUserDetailServiceTenant userService) {
		this.tokenUtil = tokenUtil;
		this.userService = userService;
	}

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException, NullPointerException {
		IJwtAuthenticationToken jwtAuthentication = (IJwtAuthenticationToken) authentication;
		
		String token = jwtAuthentication.getToken();
		if (token==null) throw new NullPointerException("JWT String argument cannot be null or empty.");
		
		try {
			String username=tokenUtil.getUsername(token);
			String tenant=tokenUtil.getTenant(token)/* : jwtAuthentication.getTenant()*/;
			
			List<GrantedAuthority> userAuthoritiesForTenant=new ArrayList<>();
			MyUser<String,List<String>> user=(MyUser<String, List<String>>) userService.loadUserByUsername(username);
			
			if (user.getTenantsId().containsKey(tenant) && !user.getTenantsId().isEmpty()) {
				for(String role:user.getTenantsId().get(tenant)) 
					userAuthoritiesForTenant.add(new SimpleGrantedAuthority(role));
			} else
				userAuthoritiesForTenant.add(new SimpleGrantedAuthority(Role.BASIC_USER.toString()));
				//userAuthoritiesForTenant.add(new SimpleGrantedAuthority("ROLE_"+Role.BASIC_USER.name()));
			
			return new JwtAuthenticationToken(
					Collections.unmodifiableList(userAuthoritiesForTenant),
					tokenUtil.refreshTokenAtHalfTime(token),
					tenant,
					user,
					user.getPassword()
					);
		}
		
		catch (UsernameNotFoundException e) {
			logger.error("Exception : {} failed : {}", token, e.getMessage());
			throw new UsernameNotFoundException(e.getMessage(),e);
		}
		catch (ExpiredJwtException e) {
			logger.error("Exception : {} failed : {}", token, e.getMessage());
			throw new BadCredentialsException(e.getMessage(),e);
		}
		catch (MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException | InvalidKeyException e) {
			logger.error("Exception : {} failed : {}", token, e.getMessage());
			throw new BadCredentialsException(e.getMessage(),e);
		}
		
		
	}
	
	
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtAuthenticationToken.class);
	}

}
