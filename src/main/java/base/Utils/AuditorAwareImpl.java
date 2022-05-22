package base.Utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import base.DTO.MyUser;

@Component("AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {
	
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication!=null && authentication.isAuthenticated()) {
			if (authentication.getPrincipal().equals("anonymousUser")) return Optional.of("anonymousUser"); //delete when security setted
			return Optional.ofNullable(((MyUser<?,?>)authentication.getPrincipal()).getUsername());
		}
		return Optional.ofNullable("anonymousUser");
	}

}
