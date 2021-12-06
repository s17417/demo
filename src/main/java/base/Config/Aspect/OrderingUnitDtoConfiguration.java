package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.baza1.OrderingUnitDTO.OrderingUnitDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.OrderingUnit;

@Component
@Aspect
@Order(0)
public class OrderingUnitDtoConfiguration implements IDtoConfigurtion {
	
	
	@Autowired @Qualifier("toUpperCaseConverter")
	Converter<String,String> toUpperCaseConverter;

	@Autowired @Qualifier ("firstLetterUpperCaseConverter")
	Converter <String,String> firstLetterUpperCaseConverter;

	private ExpressionMap<OrderingUnitDTO, OrderingUnit> expressionMap = map ->{
		map.using(toUpperCaseConverter).map(OrderingUnitDTO::getShortName, OrderingUnit::setShortName);
		map.using(firstLetterUpperCaseConverter).map(OrderingUnitDTO::getName, OrderingUnit::setName);
		map.map(OrderingUnitDTO::getAddress, OrderingUnit::setAddress);
	};
	
	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper
		.createTypeMap(OrderingUnit.class, OrderingUnitDTO.class)
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class)
		.implicitMappings();
		
		mapper
		.createTypeMap(OrderingUnitDTO.class, OrderingUnit.class)
		.addMappings(expressionMap)
		.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class);
	}
}
