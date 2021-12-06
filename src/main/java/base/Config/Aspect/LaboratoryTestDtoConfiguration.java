package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.LaboratoryTest;

@Component
@Aspect
@Order(0)
public class LaboratoryTestDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(LaboratoryTest.class, LaboratoryTestDTO.class)
		.includeBase(AbstractAuditableObject.class, ActiveObjectDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(LaboratoryTestDTO.class, LaboratoryTest.class)
		.addMappings( map ->{
			map.map(LaboratoryTestDTO::getName, LaboratoryTest::setName);
			map.map(LaboratoryTestDTO::getShortName, LaboratoryTest::setShortName);
			map.map(LaboratoryTestDTO::getDescription, LaboratoryTest::setDescription);
		});

	}

}
