package base.Utils.Multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import base.Utils.Security.JwtAuthenticationToken;
import base.Utils.Security.TokenConstant;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {	

	@Override
	public String resolveCurrentTenantIdentifier() {
		Authentication tenant=SecurityContextHolder.getContext().getAuthentication();
		return tenant!=null?((JwtAuthenticationToken)tenant).getTenant():TokenConstant.EMPTY_TOKEN_TENANT_FIELD.getValue();		
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
	
}
