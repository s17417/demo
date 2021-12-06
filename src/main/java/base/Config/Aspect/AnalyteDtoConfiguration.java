package base.Config.Aspect;

import org.modelmapper.ModelMapper;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.AnalyteDTO;
import base.Model.AbstractPersistentClasses.AbstractActiveObject;
import base.Model.baza1.Analyte;

public class AnalyteDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Analyte.class, AnalyteDTO.class)
		.implicitMappings()
		.includeBase(AbstractActiveObject.class, ActiveObjectDTO.class);
		
		mapper.createTypeMap(AnalyteDTO.class, Analyte.class)
		.addMappings(map -> {
			map.map(AnalyteDTO::getShortName, Analyte::setShortName);
			map.map(AnalyteDTO::getName, Analyte::setName);
			map.map(AnalyteDTO::getDescription, Analyte::setDescription);
		})
		.includeBase(ActiveObjectDTO.class, AbstractActiveObject.class);

	}

}
