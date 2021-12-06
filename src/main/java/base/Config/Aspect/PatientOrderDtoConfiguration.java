package base.Config.Aspect;

import java.util.Optional;
import java.util.function.Supplier;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza1.PatientDTO.SimplePatientDTO;
import base.DTO.baza1.PatientOrderDTO.AbstractOrderDTO;
import base.DTO.baza1.PatientOrderDTO.PatientOrderDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.AbstractOrder;
import base.Model.baza1.OrderResult;
import base.Model.baza1.OrderingUnit;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.Phisician;
import base.Repository.Baza1Repository.OrderingUnitRepository;
import base.Repository.Baza1Repository.PatientRepository;
import base.Repository.Baza1Repository.PhisicianRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Component
@Aspect
@Order(0)
public class PatientOrderDtoConfiguration implements IDtoConfigurtion {
	

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PhisicianRepository phisicianRepository;
	
	@Autowired
	OrderingUnitRepository orderingUnitRepository;
	

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {

		mapper.createTypeMap(AbstractOrder.class, AbstractOrderDTO.class)
		.implicitMappings()
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class);
		
		mapper.createTypeMap(PatientOrder.class, PatientOrderDTO.class)
		.implicitMappings()
		.includeBase(AbstractOrder.class, AbstractOrderDTO.class);
		
		mapper.createTypeMap(AbstractOrderDTO.class, AbstractOrder.class)
		.addMapping(AbstractOrderDTO::getOrderIdentification, AbstractOrder<OrderResult>::setOrderIdentification);
		
		mapper.createTypeMap(PatientOrderDTO.class, PatientOrder.class)
		.addMappings( map ->{
			map.map(PatientOrderDTO::getOrderDate, PatientOrder::setOrderDate);
			map.with(patientOrderProviderFactory(Patient.class)).map(src -> src.getPatient().getId(),PatientOrder::setPatient);
			map.with(patientOrderProviderFactory(Phisician.class)).map(src -> src.getPhisician().getId(),PatientOrder::setPhisician);
			map.with(patientOrderProviderFactory(OrderingUnit.class)).map(src -> src.getOrderingUnit().getId(),PatientOrder::setOrderingUnit);
			
		}).includeBase(AbstractOrderDTO.class, AbstractOrder.class); 
	}

	
	@SuppressWarnings("unchecked")
	private <T> Provider<T> patientOrderProviderFactory(Class<T> clas) {
		if (clas.equals(Patient.class))
			return p -> (T) patientRepository
					.findById(p.getSource().toString())
					.orElseThrow(() -> new EntityNotFoundException(Patient.class));
		if (clas.equals(Phisician.class))
			return p -> (T) phisicianRepository
					.findById(p.getSource().toString())
					.orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		if (clas.equals(OrderingUnit.class))
			return p -> (T) orderingUnitRepository
					.findById(p.getSource().toString())
					.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class));
		throw new IllegalArgumentException("Not Supported Type");
	}

}
