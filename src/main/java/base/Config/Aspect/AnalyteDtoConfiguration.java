package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.AnalyteDTO.AnalyteDTO;
import base.DTO.baza1.AnalyteDTO.ListAnalyteDTO;
import base.Model.AbstractPersistentClasses.AbstractActiveObject;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.Analyte;


@Component
@Aspect
@Order(0)
public class AnalyteDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Analyte.class, AnalyteDTO.class)
		.implicitMappings()
		.includeBase(AbstractActiveObject.class, ActiveObjectDTO.class);
		
		mapper.createTypeMap(Analyte.class, ListAnalyteDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(AnalyteDTO.class, Analyte.class)
		.addMappings(map -> {
			map.map(AnalyteDTO::getShortName, Analyte::setShortName);
			map.map(AnalyteDTO::getName, Analyte::setName);
			map.map(AnalyteDTO::getDescription, Analyte::setDescription);
		})
		.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class);

	}

}
