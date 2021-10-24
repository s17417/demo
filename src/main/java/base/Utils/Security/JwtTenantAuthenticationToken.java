package base.Utils.Security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtTenantAuthenticationToken extends JwtAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTenantAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String tenant,
			Object Principal, Object credentials) {
		super(authorities, null, tenant, Principal, credentials);
		// TODO Auto-generated constructor stub
	}
	
	public JwtTenantAuthenticationToken(String tenant) {
		super(null, null, tenant, null, null);
		// TODO Auto-generated constructor stub
	}

}
