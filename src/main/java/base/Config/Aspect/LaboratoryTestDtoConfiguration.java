package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.Model.AbstractPersistentClasses.AbstractActiveObject;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.SpecimentType;

@Component
@Aspect
@Order(0)
public class LaboratoryTestDtoConfiguration implements IDtoConfigurtion {

	Provider<SpecimentType> specimentTypeProvider;
	
	Condition<Object, Object> objectNotNullCondition;

	public LaboratoryTestDtoConfiguration(Provider<SpecimentType> specimentTypeProvider,
			Condition<Object, Object> objectNotNullCondition) {
		this.specimentTypeProvider = specimentTypeProvider;
		this.objectNotNullCondition = objectNotNullCondition;
	}



	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(LaboratoryTest.class, LaboratoryTestDTO.class)
		.includeBase(AbstractActiveObject.class, ActiveObjectDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(LaboratoryTestDTO.class, LaboratoryTest.class)
		.addMappings( map ->{
			map.map(LaboratoryTestDTO::getName, LaboratoryTest::setName);
			map.map(LaboratoryTestDTO::getShortName, LaboratoryTest::setShortName);
			map.when(objectNotNullCondition).with(specimentTypeProvider).map(src -> src.getSpecimentType().getId(), LaboratoryTest::setSpecimentType);
			map.map(LaboratoryTestDTO::getDescription, LaboratoryTest::setDescription);
		});

	}

}
