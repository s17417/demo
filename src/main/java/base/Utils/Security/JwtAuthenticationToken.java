package base.Utils.Security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import base.Model.baza.AdminRole;
import base.Model.baza.Role;

public class JwtAuthenticationToken extends AbstractAuthenticationToken implements IJwtAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	
	private Object principal;
	
	private Object credentials;
	
	private String tenant;
	
	/**
	 * This constructor can by used in any place of app, it populates token, and is primary form passed to Authentication Manager / Authentication Provider
	 * @param token
	 */
	public JwtAuthenticationToken(String token) {
		super(new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(AdminRole.BASIC_USER.toString()))));
		this.token = token;
	}
	
	/**
	 * This constructor should only be used by Authentication Provider specific for this class or Authentication Manager.
	 * @param authorities
	 * @param token
	 * @param Principal
	 * @param credentials
	 */
	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String token, String tenant, Object Principal, Object credentials) {
		super(authorities);
		this.token = token;
		this.principal=Principal;
		this.credentials=credentials;
		this.tenant=tenant;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	@Override
	public String getToken() {
		return token;
	}

	@Override
	public String getTenant() {
		return tenant;
	}
}
