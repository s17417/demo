package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.DTOObjectConstans;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza.TenantDTO;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza.Tenant;

@Component
@Aspect
@Order(0)
public class TenantDtoConfiguration implements IDtoConfigurtion {
	
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
					Tenant.class,
					TenantDTO.class
					)
			.implicitMappings();
		
		mapper
			.createTypeMap(
					TenantDTO.class,
					Tenant.class,
					DTOObjectConstans.CREATE.name()
					)
			.addMappings( map -> {
				map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
			}
		)
			.includeBase(PersistenceObjectDTO.class,AbstractPersistentObject.class);
	
		mapper
			.createTypeMap(
					TenantDTO.class,
					Tenant.class,
					DTOObjectConstans.UPDATE.name())
			.addMappings( map -> {
				map.using(firstLetterUpperCaseConverter).map(TenantDTO::getName, Tenant::setName);
			}
		);
	}

}
