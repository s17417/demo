package base.Config.Aspect;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.CommentDTO;
import base.DTO.baza1.PhisicianDTO.PhisicianDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.Address;
import base.Model.baza1.PatientComment;
import base.Model.baza1.Phisician;

@Component
@Aspect
@Order(0)
public class PhisicianDtoConfiguration implements IDtoConfigurtion {
	
	@Autowired @Qualifier("toUpperCaseConverter")
	Converter<String,String> toUpperCaseConverter;

	@Autowired @Qualifier ("firstLetterUpperCaseConverter")
	Converter <String,String> firstLetterUpperCaseConverter;
	
	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Phisician.class, PhisicianDTO.class)
		.implicitMappings()
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class);
		
		/*mapper.createTypeMap(Phisician.class, PhisicianDTO.class)
		.addMapping(Phisician::getOrderingUnits, PhisicianDTO::setOrderingUnits)
		.implicitMappings()
		.includeBase(Phisician.class, SimplePhisicianDTO.class);*/
		
		mapper.createTypeMap(PhisicianDTO.class, Phisician.class).addMappings(map -> {
			map.map(PhisicianDTO::getName, Phisician::setName);
			map.map(PhisicianDTO::getSurname, Phisician::setSurname);
			map.map(PhisicianDTO::getPersonalIdentificationNumber, Phisician::setPersonalIdentificationNumber);
		});
		
		
	}

}
