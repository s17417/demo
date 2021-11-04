package base.Services.baza;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import base.DTO.DTOObjectConstans;
import base.DTO.baza.TenantDTO;
import base.Model.baza.Role;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Utils.IDataSourceGenerator;
import base.Utils.IdGenerator;
import base.Utils.Security.JwtAuthenticationToken;

@Validated
@Service
public class TenantService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private TenantRepository tenantRepo;
	
	@Autowired
	private IDataSourceGenerator dataSourceGenerator;
	
	private final ExampleMatcher userMatcher;

	
	{
		List<String> list= Arrays.stream(Users.class.getDeclaredFields())
				.filter(p -> p.getName()!="email")
				.map(field -> field.getName())
				.collect(Collectors.toList());
		list.addAll(
				Arrays.stream(Users.class.getSuperclass().getDeclaredFields())
				.filter(p -> p.getName()!="email")
				.map(field -> field.getName())
				.collect(Collectors.toList())
				);
		
		userMatcher = ExampleMatcher
				.matching()
				.withIgnorePaths(list.toArray(new String[list.size()]))
				.withMatcher("email", ExampleMatcher.GenericPropertyMatcher.of(StringMatcher.EXACT, true));
	}
	
	@Transactional
	public TenantDTO createTenant(@NotNull TenantDTO tenantDTO) {
		var user = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		var tenant = modelMapper.getTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.CREATE.name()).map(tenantDTO);
		user.addTenant(tenant, Role.SPECIFIC_DATABASE_ADMIN);
		tenant.setDatabasePassword(IdGenerator.getId().toString());
		tenant.setDatabseUserName(tenant.getName());
		var savedTenant = tenantRepo.saveAndFlush(tenant);
		
		
		modelMapper.getTypeMap(Tenant.class, TenantDTO.class).map(savedTenant, tenantDTO);
		dataSourceGenerator.tenantDatabaseInitialization(savedTenant);
		
		return tenantDTO;
	}
	
	public TenantDTO updateTenant (@NotNull TenantDTO tenantDTO) {
		var tenant = tenantRepo.findById(tenantDTO.getId());
		var loggedTenant= ((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		
		if (tenant.isEmpty())
			throw new NullPointerException("tenant: "+tenantDTO.getId()+" doesn't exists");
		if (!tenant.get().getName().contentEquals(loggedTenant))
			throw new IllegalArgumentException("tenant: "+tenantDTO.getId()+" doesn't match users signed in tenant");
		System.out.println(tenant.get());
		modelMapper.getTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.UPDATE.name()).map(tenantDTO, tenant.get());
		tenantRepo.save(tenant.get());
		modelMapper.getTypeMap(Tenant.class, TenantDTO.class).map(tenant.get(), tenantDTO);
		return tenantDTO;
	}
	
	@Transactional
	public void deleteTenant() throws IllegalArgumentException {
		var loggedTenant= ((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication());
		var tenant=tenantRepo.findByName(loggedTenant.getTenant());	
		if (tenant==null)
			throw new NullPointerException(tenant+"doesn't exists");	
		dataSourceGenerator.tenantDatabaseDeletion(tenant);
		tenantRepo.delete(tenant);
	}
	
	public TenantDTO getTenant() {
		var loggedTenant = ((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getTenant();
		return modelMapper.getTypeMap(Tenant.class,TenantDTO.class).map(tenantRepo.findByName(loggedTenant));
	}
	
	public void deleteInvitation(@NotNull @Email String email){
		
	}
	

}
