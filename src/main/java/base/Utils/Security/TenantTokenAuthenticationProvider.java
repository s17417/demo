package base.Utils.Security;

import java.security.InvalidKeyException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import base.DTO.MyUser;
import base.Model.baza.Role;
import base.Services.baza.IUserDetailServiceTenant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TenantTokenAuthenticationProvider  implements AuthenticationProvider {
	

	private IUserDetailServiceTenant userService;
	private Logger logger=LoggerFactory.getLogger(TokenAuthenticationProvider.class);
	
	public TenantTokenAuthenticationProvider(IUserDetailServiceTenant userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var jwtTenantAuthenticationToken = (JwtTenantAuthenticationToken) authentication;
		var user = SecurityContextHolder.getContext().getAuthentication();
		var tenant = jwtTenantAuthenticationToken.getTenant();
		
		try {
		if (tenant==null) throw new UsernameNotFoundException("relation N/A or invitation not accepted yet");
		var userTenant = userService.getAuthortiestForTenant(user.getName(), tenant);
		
		if (userTenant.isEmpty()) throw new BadCredentialsException("relation N/A or invitation not accepted yet");
		
		List<GrantedAuthority> list= userTenant.stream()
				.filter(authority -> !authority.getAuthority().contentEquals(Role.SPECIFIC_DATABASE_INVITATION.toString()))
				.collect(Collectors.toList());
		list.add(new SimpleGrantedAuthority(Role.BASIC_USER.toString()));
		if (list.isEmpty()) throw new BadCredentialsException("relation N/A or invitation not accepted yet");
		
		return new JwtTenantAuthenticationToken(
				list,
				tenant,
				user.getPrincipal(),
				user.getCredentials()
				);
		
		} catch (UsernameNotFoundException e) {
			logger.error("Exception : {} failed : {}", tenant, e.getMessage());
			throw new UsernameNotFoundException(e.getMessage(),e);
		}
		catch (ExpiredJwtException e) {
			logger.error("Exception : {} failed : {}", tenant, e.getMessage());
			throw new BadCredentialsException(e.getMessage(),e);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtTenantAuthenticationToken.class);
	}

}
