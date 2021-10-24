package base.Config;

import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import base.DTO.DTOObjectConstans;
import base.DTO.baza.TenantDTO;
import base.DTO.baza.UserDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;

//import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class Config {

	@Bean
	public Converter<String,String> toLowerCaseConverter() {
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().toLowerCase() : null;
	}
	
	@Bean
	public Converter<String,String> firstLetterUpperCaseConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().substring(0, 1).toUpperCase() + mapper.getSource().strip().toLowerCase().substring(1) : null;
	}
	
	@Bean
	public Converter<String,String> stripConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip():null;
	}
	
	@Bean
	public Condition<String, String> stringNotNullCondition(){
		return condition -> condition.getSource()!=null;
		
	}
	
	/*@Bean
	public ObjectMapper getJSONObjectMapper() {
		return new ObjectMapper();
	}*/
	
	@Bean
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	}
	
	@Bean
	public ModelMapper modelMapper(
			@Autowired @Qualifier("toLowerCaseConverter") Converter<String,String> toLowerCaseConverter,
			@Autowired @Qualifier ("firstLetterUpperCaseConverter") Converter <String,String> firstLetterUpperCaseConverter,
			@Autowired @Qualifier ("stripConverter") Converter<String,String> stripConverter,
			@Autowired @Qualifier ("stringNotNullCondition") Condition<String,String> stringNotNullCondition
			) {
		var mapper=new ModelMapper();
		
		mapper.createTypeMap(Users.class, UserDTO.class)
		.addMappings(map -> {
			map.skip(UserDTO::setPassword);
			}
		);
		
		mapper.createTypeMap(UsersTenantRole.class, UserTenantRoleDTO.class)/*.addMappings( property ->{
			property.map(UsersTenantRole::getUser, UserWithRoleDTO::setUser);
		})*/;
		
		mapper.createTypeMap(UserDTO.class, Users.class, DTOObjectConstans.CREATE.name())
		.addMappings(map -> {
			map.skip(Users::setId);
			map.using(toLowerCaseConverter).map(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			}
		);
		
		mapper.createTypeMap(UserDTO.class, Users.class,DTOObjectConstans.UPDATE.name())
		.addMappings( map -> {
			map.using(stripConverter).map(UserDTO::getId, Users::setId);
			map.skip(UserDTO::getEmail, Users::setEmail);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getFirstname, Users::setFirstname);
			map.using(firstLetterUpperCaseConverter).map(UserDTO::getSurname, Users::setSurname);
			map.when(stringNotNullCondition).map(UserDTO::getPassword, Users::setPassword);
			}
		);
		
		mapper.createTypeMap(Tenant.class, TenantDTO.class);
		
		mapper.createTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.CREATE.name())
		.addMappings( map -> {
			map.skip(TenantDTO::getId, Tenant::setId);
			map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
			}
		);
		
		mapper.createTypeMap(TenantDTO.class, Tenant.class, DTOObjectConstans.UPDATE.name())
		.addMappings( map -> {
			map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
		});
		
		//mapper.createTypeMap(UserWithRoleDTO, null)
	
	    return mapper;
	}
	
}
