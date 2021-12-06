package base.Config.Aspect;

import java.util.List;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.PatientDTO.SimplePatientWithCollectionsDTO;
import base.DTO.baza1.PatientDTO.SimplePatientDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.Address;
import base.Model.baza1.Patient;

@Component
@Aspect
@Order(0)
public class PatientDtoConfiguration implements IDtoConfigurtion {

	@Autowired @Qualifier("adressDtoListToEntityConverter")
	Converter<List<AddressDTO>,List<Address>> adressDtoListToEntityConverter;
	
	@Autowired @Qualifier("toUpperCaseConverter")
	Converter<String,String> toUpperCaseConverter;

	@Autowired @Qualifier ("firstLetterUpperCaseConverter")
	Converter <String,String> firstLetterUpperCaseConverter;
	
	@Autowired
	Converter<List<String>,List<String>> stringListConverter;


	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Patient.class, SimplePatientDTO.class).implicitMappings()
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(Patient.class, SimplePatientWithCollectionsDTO.class).implicitMappings()
		.includeBase(Patient.class, SimplePatientDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(SimplePatientDTO.class, Patient.class).addMappings(map -> {
			map.using(firstLetterUpperCaseConverter).map(SimplePatientDTO::getName, Patient::setName);
			map.using(firstLetterUpperCaseConverter).map(SimplePatientDTO::getSurname, Patient::setSurname);
			map.map(SimplePatientDTO::getDateOfBirth, Patient::setDateOfBirth);
			map.using(toUpperCaseConverter).map(SimplePatientDTO::getPersonalIdentificationNumber, Patient::setPersonalIdentificationNumber);
		})
		.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class);
		
		mapper.createTypeMap(SimplePatientWithCollectionsDTO.class, Patient.class).addMappings(map -> {
			map.using(adressDtoListToEntityConverter).map(SimplePatientWithCollectionsDTO::getAddresses, Patient::setAddresses);
			map.using(stringListConverter).map(SimplePatientWithCollectionsDTO::getPhoneNumbers, Patient::setPhoneNumbers);
		})
		.includeBase(SimplePatientDTO.class, Patient.class);
	}

}
