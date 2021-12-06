package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza.UsersTenantRole;

@Component
@Aspect
@Order(-1)
public class UserTenantDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		
		mapper
			.createTypeMap(
					UsersTenantRole.class,
					UserTenantRoleDTO.class
					).addMappings(map -> {
						map.<String>map(src -> src.getUser().getEmail(), (dest,v) -> dest.getUser().setEmail(v));
						map.<String>map(src -> src.getUser().getId(), (dest,v) -> dest.getUser().setId(v));
						map.<String>map(src -> src.getTenant().getName(), (dest,v) -> dest.getTenant().setName(v));
						map.<String>map(src -> src.getTenant().getId(), (dest,v) -> dest.getTenant().setId(v));
						map.map(UsersTenantRole::getRole, UserTenantRoleDTO::setRole);
					})
			.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class)
			.includeBase(AbstractPersistentObject.class, PersistenceObjectDTO.class);
		
		mapper
			.createTypeMap(
					UserTenantRoleDTO.class,
					UsersTenantRole.class,
					DTOObjectConstans.UPDATE.name()
					)
			.addMappings(map ->{
				map.skip(UserTenantRoleDTO::getUser, UsersTenantRole::setUser);
				map.skip(UserTenantRoleDTO::getUser, UsersTenantRole::setTenant);
				map.map(UserTenantRoleDTO::getRole, UsersTenantRole::setRole);
				map.map(UserTenantRoleDTO::getId, UsersTenantRole::setId);
			}
		).includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class)
			.includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class);		
	}	
}
