package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza.TenantDTO;
import base.DTO.baza.UserDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;

@Component
@Aspect
@Order(0)
public class UsersDtoConfiguration implements IDtoConfigurtion {
	
	@Autowired @Qualifier("toLowerCaseConverter")
	Converter<String,String> toLowerCaseConverter;

	@Autowired @Qualifier ("firstLetterUpperCaseConverter")
	Converter <String,String> firstLetterUpperCaseConverter;
	
	@Autowired @Qualifier ("stripConverter")
	Converter<String,String> stripConverter;
	
	@Autowired @Qualifier ("stringNotNullCondition")
	Condition<String,String> stringNotNullCondition;

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Users.class, UserDTO.class)
		.addMappings(map -> {
			map.skip(UserDTO::setPassword);
			map.map(Users::getEmail, UserDTO::setEmail);
			map.map(Users::getFirstname, UserDTO::setFirstname);
			map.map(Users::getSurname, UserDTO::setSurname);
			map.map(Users::getCreationTimeStamp, UserDTO::setCreationTimeStamp);
			map.map(Users::getUpdateTimeStamp, UserDTO::setUpdateTimeStamp);
			}
		)
		.includeBase(AbstractPersistentObject.class, PersistenceObjectDTO.class);
		
		mapper.createTypeMap(UserDTO.class, Users.class, DTOObjectConstans.CREATE.name())
		.addMappings(map -> {
			map.using(toLowerCaseConverter).map(UserDTO::getEmail, Users::setEmail);
			map.map(UserDTO::getPassword, Users::setPassword);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			}
		)
		.includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class);
		
		mapper.createTypeMap(UserDTO.class, Users.class,DTOObjectConstans.UPDATE.name())
		.addMappings( map -> {
			//map.using(stripConverter).map(UserDTO::getId, Users::setId);
			map.skip(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			map.when(stringNotNullCondition).map(UserDTO::getPassword, Users::setPassword);
			}
		)
		.includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class);
		
		mapper.createTypeMap(UserDTO.class, Users.class)
		.addMappings( map -> {
			//map.using(stripConverter).map(UserDTO::getId, Users::setId);
			map.skip(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			map.when(stringNotNullCondition).map(UserDTO::getPassword, Users::setPassword);
			}
		);
		
		
		
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	public void modelMapperConfiguration2(ModelMapper mapper) {
		
		mapper.createTypeMap(Users.class, UserDTO.class)
		.addMappings(map -> {
			map.skip(UserDTO::setPassword);
			map.map(Users::getEmail, UserDTO::setEmail);
			}
		).includeBase(AbstractPersistentObject.class, PersistenceObjectDTO.class);
		
		mapper.createTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class).includeBase(AbstractPersistentObject.class, PersistenceObjectDTO.class).implicitMappings();
		mapper.createTypeMap(UserTenantRoleDTO.class, UsersTenantRole.class,DTOObjectConstans.UPDATE.name()).addMappings(map ->{
			map.skip(UserTenantRoleDTO::getUser, UsersTenantRole::setUser);
			map.skip(UserTenantRoleDTO::getUser, UsersTenantRole::setTenant);
			map.map(UserTenantRoleDTO::getRole, UsersTenantRole::setRole);
			map.map(UserTenantRoleDTO::getId, UsersTenantRole::setId);
			}
		).includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class).includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class);
		
	
		mapper.createTypeMap(UserDTO.class, Users.class, DTOObjectConstans.CREATE.name())
		.addMappings(map -> {
			map.skip(Users::setId);
			map.using(toLowerCaseConverter).map(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			}
		).implicitMappings();
		
		mapper.createTypeMap(UserDTO.class, Users.class,DTOObjectConstans.UPDATE.name())
		.addMappings( map -> {
			map.using(stripConverter).map(UserDTO::getId, Users::setId);
			map.skip(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			map.when(stringNotNullCondition).map(UserDTO::getPassword, Users::setPassword);
			}
		).implicitMappings();
		
		mapper.createTypeMap(Tenant.class, TenantDTO.class).implicitMappings();
		
		mapper.createTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.CREATE.name())
		.addMappings( map -> {
			//map.skip(TenantDTO::getId, Tenant::setId);
			map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
			}
		).includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class).implicitMappings();
	
		mapper.createTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.UPDATE.name())
		.addMappings( map -> {
			map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
		}).implicitMappings();
	}

	 */

}
