package base.Services.baza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza.TenantDTO;
import base.DTO.baza.UpdateUserTenantRoleDTO;
import base.DTO.baza.UserDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.baza.Role;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Repository.BazaRepository.UsersTenantRoleRepository;
import base.Utils.Security.JwtAuthenticationToken;

@Component
public class UserTenantService {
	
	@Autowired
	private UsersTenantRoleRepository usersTenantRoleRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public List<UserTenantRoleDTO> getTenantUsers() {
		var tenantName = ((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		var tenant = tenantRepository.findByName(tenantName);
		if (tenant==null)
			throw new NullPointerException("tenant: "+tenantName+" doesn't exists");
	
		var list=usersTenantRoleRepository.findByTenant(tenant);
		return list
				.stream()
				.map(e -> modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(e))
				.collect(Collectors.toList());
	}
	
	public List<UserTenantRoleDTO> getUserTenants(){
		var userName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getName();
		var user = userRepository.findByEmail(userName);
		if (user == null)
			throw new NullPointerException("user: "+userName+" doesn't exists");
		return user.getUsersTenantRole()
				.stream()
				.map(e -> modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(e))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public UpdateUserTenantRoleDTO updateUserTenantRole(UpdateUserTenantRoleDTO updateUserTenantRoleDTO) {
		var tenantName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		var tenant=tenantRepository.findByName("tenant");
		var userTenantRole = usersTenantRoleRepository.findById(updateUserTenantRoleDTO.getId());
		if (!tenant.getUsersTenantRole().contains(userTenantRole)) throw new IllegalArgumentException("Relation N/A or no such user");
		model
		
	}
	
	public List<Role> getRoles(){
		return Arrays
				.stream(Role.values())
				.filter(e -> e.name().contains("DATABASE"))
				.collect(Collectors.toList());
	}
	
	public List<Role> getAdminRoles(){
		return Arrays
				.stream(Role.values())
				.collect(Collectors.toList());
	}
	
	
}
