package base.Services.baza;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.DTOObjectConstans;
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
	public UserTenantRoleDTO updateUserTenantRole(UserTenantRoleDTO updateUserTenantRoleDTO) {
		var tenantName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		var tenant=tenantRepository.findByName(tenantName);
		var userTenantRole = usersTenantRoleRepository.findById(updateUserTenantRoleDTO.getId());
		
		if (userTenantRole.isEmpty() || !tenant.getUsersTenantRole().contains(userTenantRole.get())) 
			throw new IllegalArgumentException("Relation N/A or no such user");
		
		if (userTenantRole.get().getRole().equals(Role.SPECIFIC_DATABASE_ADMIN)&&!updateUserTenantRoleDTO.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN.name()) ) {
		List<UsersTenantRole> admins = tenant.getUsersTenantRole()
				.stream()
				.filter(e -> e.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN))
				.collect(Collectors.toList());
		if (admins.size()<2 && admins.contains(userTenantRole.get()))
			throw new IllegalArgumentException("There must be at least 1 Admin relation");
		}
		
		modelMapper.getTypeMap(UserTenantRoleDTO.class, UsersTenantRole.class, DTOObjectConstans.UPDATE.name()).map(updateUserTenantRoleDTO, userTenantRole.get());	
		//userTenantRole.get().setRole(Role.valueOf(Role.class, updateUserTenantRoleDTO.getRole()));
		var updatedObject = usersTenantRoleRepository.saveAndFlush(userTenantRole.get());
		System.out.println(updatedObject.getCreatedBy());
		System.out.println(updatedObject.getLastModifiedBy());

		modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(updatedObject,updateUserTenantRoleDTO);
		return updateUserTenantRoleDTO;
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
