package base.Config.Aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.DTOObjectConstans;
import base.DTO.baza.AuditableObjectDTO;
import base.DTO.baza.PersistenceObjectDTO;
import base.DTO.baza.TenantDTO;
import base.DTO.baza.UserDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import net.sf.ehcache.store.StoreQuery.Ordering;

@Component
@Aspect
@Order(1)
public class PersistentAuditableDtoConfiguration implements IDtoConfigurtion {
	
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
		mapper
			.createTypeMap(
					AbstractPersistentObject.class,
					PersistenceObjectDTO.class
					)
			.implicitMappings();
		mapper
			.createTypeMap(
					PersistenceObjectDTO.class,
					AbstractPersistentObject.class,
					DTOObjectConstans.UPDATE.name()
					)
			.addMappings( map -> {
				map.using(stripConverter).map(PersistenceObjectDTO::getId, AbstractPersistentObject::setId);
				}
			);
		mapper
			.createTypeMap(
					PersistenceObjectDTO.class,
					AbstractPersistentObject.class,
					DTOObjectConstans.CREATE.name()
					);
		
		mapper
			.createTypeMap(
					AbstractAuditableObject.class,
					AuditableObjectDTO.class
					).implicitMappings();		
		mapper
			.createTypeMap(
					AuditableObjectDTO.class,
					AbstractAuditableObject.class,
					DTOObjectConstans.UPDATE.name()
					);
		mapper
			.createTypeMap(
					AuditableObjectDTO.class,
					AbstractAuditableObject.class,
					DTOObjectConstans.CREATE.name()
					);
	}
}
