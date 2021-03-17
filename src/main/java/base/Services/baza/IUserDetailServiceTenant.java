package base.Services.baza;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import base.DTO.MyUser;

public interface IUserDetailServiceTenant extends UserDetailsService {
	
	public <T extends GrantedAuthority> List<T> getAuthortiestForTenant(String username, String tenant);

	@Override
	public MyUser<String,List<String>> loadUserByUsername(String username) throws UsernameNotFoundException;

	
}
