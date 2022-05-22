package base.Services.baza;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.DTOObjectConstans;
import base.DTO.baza.UpdateUserTenantRoleDTO;
import base.DTO.baza.UserSignedTenantDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.baza.Role;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Repository.BazaRepository.UsersTenantRoleRepository;
import base.Utils.Exceptions.EntityNotFoundException;
import base.Utils.Exceptions.RelationNotFoundException;
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
			throw new EntityNotFoundException(Tenant.class);
	
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
			throw new EntityNotFoundException(Users.class);
		return user.getUsersTenantRole()
				.stream()
				.map(e -> modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(e))
				.collect(Collectors.toList());
	}
	
	public UserSignedTenantDTO getUserTenant() {
		var user=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication());		
		System.out.println(user.getName()+", "+user.getTenant());
		if (user == null) throw new EntityNotFoundException(Users.class);
		var userTenantRole = usersTenantRoleRepository.findByUsersEmailAndTenantName(user.getName(), user.getTenant());
		if (userTenantRole.isEmpty()) throw new RelationNotFoundException(Users.class, Tenant.class);
		return modelMapper.map(userTenantRole.get(0), UserSignedTenantDTO.class);
	}
	
	
	@Transactional
	public UserTenantRoleDTO updateUserTenantRole(UserTenantRoleDTO updateUserTenantRoleDTO) {
		var tenantName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		var tenant=tenantRepository.findByName(tenantName);
		var userTenantRole = usersTenantRoleRepository.findById(updateUserTenantRoleDTO.getId()).orElseThrow(() -> new EntityNotFoundException(UsersTenantRole.class));
		
		if (!tenant.getUsersTenantRole().contains(userTenantRole)) 
			throw new IllegalArgumentException("Relation N/A or no such user");
		
		if (userTenantRole.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN)&&!updateUserTenantRoleDTO.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN.name()) ) {
		List<UsersTenantRole> admins = tenant.getUsersTenantRole()
				.stream()
				.filter(e -> e.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN))
				.collect(Collectors.toList());
		if (admins.size()<2 && admins.contains(userTenantRole))
			throw new IllegalArgumentException("There must be at least 1 Admin relation");
		}
		
		modelMapper.getTypeMap(UserTenantRoleDTO.class, UsersTenantRole.class, DTOObjectConstans.UPDATE.name()).map(updateUserTenantRoleDTO, userTenantRole);	
		//userTenantRole.get().setRole(Role.valueOf(Role.class, updateUserTenantRoleDTO.getRole()));
		var updatedObject = usersTenantRoleRepository.saveAndFlush(userTenantRole);

		modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(updatedObject,updateUserTenantRoleDTO);
		return updateUserTenantRoleDTO;
	}
	
	@Transactional
	public UserTenantRoleDTO acceptInvitation(String userTenantId) throws RelationNotFoundException {
		var userName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getName();
		var user = userRepository.findByEmail(userName);
		
		
		
		var userTenantRole = usersTenantRoleRepository.findById(userTenantId).orElseThrow(() -> new RelationNotFoundException(Users.class,Tenant.class));
		if (!user.getUsersTenantRole().contains(userTenantRole)) throw new RelationNotFoundException(Users.class,Tenant.class);
		userTenantRole.setRole(Role.SPECIFIC_DATABASE_VISITOR);
		return modelMapper.getTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).map(usersTenantRoleRepository.saveAndFlush(userTenantRole));	
	}
	
	public void deleteUserTenant(String userTenantId) throws EntityNotFoundException{
		var userName=((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getName();
		var user = userRepository.findByEmail(userName);
		var utr = usersTenantRoleRepository.findById(userTenantId).orElseThrow(() -> new EntityNotFoundException(UsersTenantRole.class));
		
		if (user.getUsersTenantRole().stream().anyMatch(obj -> obj.getTenant().equals(utr.getTenant()))) 
			usersTenantRoleRepository.delete(utr);
		else throw new IllegalArgumentException("Cant, remove itself or relation N/A");
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
