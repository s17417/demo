package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza1.CommentDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza1.PatientComment;

@Component
@Aspect
@Order(0)
public class CommentDtoConfiguration implements IDtoConfigurtion {

	@Autowired @Qualifier ("stripConverter")
	Converter<String,String> stripConverter;
	
	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(PatientComment.class, CommentDTO.class)
				.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class).implicitMappings();
		
		mapper.createTypeMap(CommentDTO.class, PatientComment.class, DTOObjectConstans.CREATE.name()).addMappings(map -> {
			map.using(stripConverter).map(CommentDTO::getComment, PatientComment::setComment);
		})
			.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class)
			.includeBase(PersistenceObjectDTO.class, AbstractPersistentObject.class);
	}

}
