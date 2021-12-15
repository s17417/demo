package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.baza1.OrdersDTO.AbstractOrderDTO;
import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.baza1.AbstractOrder;
import base.Model.baza1.LabQualityControl;
import base.Model.baza1.OrderResult;
import base.Model.baza1.OrderingUnit;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.Phisician;
import base.Utils.Exceptions.EntityNotFoundException;

@Component
@Aspect
@Order(0)
public class OrdersDtoConfiguration implements IDtoConfigurtion {
	
	Converter<String,String> stripConverter;
	
	Provider<Patient> patientProvider;
	
	Provider<Phisician> phisicianProvider;
	
	Provider<OrderingUnit> orderingUnitProvider;
	
	public OrdersDtoConfiguration(
			Converter<String,String> stripConverter,
			Provider<Patient> patientProvider,
			Provider<Phisician> phisicianProvider,
			Provider<OrderingUnit> orderingUnitProvider) {
		this.stripConverter = stripConverter;
		this.patientProvider = patientProvider;
		this.phisicianProvider = phisicianProvider;
		this.orderingUnitProvider = orderingUnitProvider;
	}
	
	@Override
	public void modelMapperConfiguration(ModelMapper mapper) throws EntityNotFoundException {

		mapper.createTypeMap(AbstractOrder.class, AbstractOrderDTO.class)
		.implicitMappings()
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class);
		
		mapper.createTypeMap(PatientOrder.class, PatientOrderDTO.class)
		.implicitMappings()
		.includeBase(AbstractOrder.class, AbstractOrderDTO.class);
		
		mapper.createTypeMap(LabQualityControl.class, LabQualityControlDTO.class)
		.implicitMappings()
		.includeBase(AbstractOrder.class, AbstractOrderDTO.class);
		
		mapper.createTypeMap(AbstractOrderDTO.class, AbstractOrder.class)
		.addMapping(AbstractOrderDTO::getOrderIdentification, AbstractOrder<OrderResult>::setOrderIdentificationCode);
		
		mapper.createTypeMap(PatientOrderDTO.class, PatientOrder.class)
		.addMappings( map ->{
			map.map(PatientOrderDTO::getOrderDate, PatientOrder::setOrderDate);
			map.with(patientProvider).map(src -> src.getPatient().getId(),PatientOrder::setPatient);
			map.with(phisicianProvider).map(src -> src.getPhisician().getId(),PatientOrder::setPhisician);
			map.with(orderingUnitProvider).map(src -> src.getOrderingUnit().getId(),PatientOrder::setOrderingUnit);
			
		}).includeBase(AbstractOrderDTO.class, AbstractOrder.class); 
		
		mapper.createTypeMap(LabQualityControlDTO.class, LabQualityControl.class)
		.addMappings( map ->{
			map.using(stripConverter).map(LabQualityControlDTO::getName, LabQualityControl::setName);
			map.using(stripConverter).map(LabQualityControlDTO::getExternalIdentificationCode, LabQualityControl::setExternalIdentificationCode);
			map.map(LabQualityControlDTO::getExpirationDate, LabQualityControl::setExpirationDate);
			map.map(LabQualityControlDTO::getReportingDeadLine, LabQualityControl::setReportingDeadLine);
			map.using(stripConverter).map(LabQualityControlDTO::getDescription, LabQualityControl::setDescription);
		}).includeBase(AbstractOrderDTO.class, AbstractOrder.class); 
	}
}
