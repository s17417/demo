package base.Services.baza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.MyUser;
import base.DTO.MyUserImpl;
import base.Model.baza.Role;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.UsersRepository;
import base.Repository.BazaRepository.UsersTenantRoleRepository;

/**
 * Implementation of UserDetailService
 * @author Tomasz Poławski
 *
 */
@Service
public class UserDetailServiceImpl implements IUserDetailServiceTenant {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private UsersTenantRoleRepository usrTntRlRepository;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	@Override
	public MyUser<String,List<String>> loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user= userRepository.findByName(username);
		if (user==null) throw new UsernameNotFoundException("nie ma takiego chujka");
		
		Map<String,List<String>> tenantsId=user.getUsersTenantRole()
				.stream()
				.map(tenant -> tenant.getTenant().getName())
				.distinct()
				.collect(Collectors.toMap(k -> k,v-> new ArrayList<String>()));
		user.getUsersTenantRole()
				.stream()
				.forEach(e -> tenantsId.get(e.getTenant().getName()).add(e.getRole().name()));
		
		return MyUserImpl.myUserBuilder()
				.username(user.getName())
				.password(user.getPassword())
				.tenantsId(Collections.unmodifiableMap(tenantsId))
				.accountExpired(!user.getAccountNonExpired())
				.accountLocked(!user.getAccountNonLocked())
				.credentialsExpired(!user.getCredentialsNonExpired())
				.disabled(!user.getEnabled())
				.authorities("ROLE_"+Role.BASIC_USER.name())
				.build();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	@Override
	public List<GrantedAuthority> getAuthortiestForTenant(String username, String tenant) {
		List<UsersTenantRole> userTenantRoles=usrTntRlRepository.findByUsersNameAndTenantName(username, tenant);
		List<GrantedAuthority> authorities = null;
		if (tenant!=null) {
			
			authorities=userTenantRoles
					.stream()
					.map(e -> new SimpleGrantedAuthority("ROLE_"+e.getRole().name()))
					.collect(Collectors.toList());
			return authorities;
		}
		return new ArrayList<GrantedAuthority>();
	} 

}
