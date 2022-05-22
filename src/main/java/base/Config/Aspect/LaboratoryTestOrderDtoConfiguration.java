package base.Config.Aspect;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.core.Conventions;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.PersistenceObjectDTO;
import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.AnalyteResultDTO.QuantitativeAnalyteResultDTO;
import base.DTO.baza1.MethodDTO.QuantitativeFormatMethodDTO;
import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.DTO.baza1.PatientSampleDTO.ControlSampleDTO;
import base.DTO.baza1.PatientSampleDTO.PatientSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SimpleControlSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SpecimentTypeDTO;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultWithListDTO;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultDTO;
import base.DTO.baza1.labTestOrderDTO.LabTestOrderDTO;
import base.DTO.baza1.labTestOrderDTO.ListOrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultWithResultListDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleLabQualityControlResultDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleOrderResultDTO;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.ControlSample;
import base.Model.baza1.LabQualityControl;
import base.Model.baza1.LabQualityControlResult;
import base.Model.baza1.LabTestOrder;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.OrderResult;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.PatientSample;
import base.Model.baza1.QuantitativeAnalyteResult;
import base.Model.baza1.SpecimentType;
import base.Utils.Exceptions.EntityNotFoundException;

@Component
@Aspect
@Order(0)
public class LaboratoryTestOrderDtoConfiguration implements IDtoConfigurtion {

	Provider<LaboratoryTest> laboratoryTestProvider;
	Provider<PatientSample> patientSampleProvider;
	Provider<SpecimentType> specimentTypeProvider;
	
	/*Converter<List<? extends AbstractAnalyteResultDTO<?, ?>>, Set<? extends AbstractAnalyteResult<?,?>>> converter = conv ->{		
		conv.getSource()
			.stream()
			.map(srcObj -> conv.getMappingEngine()
					.map(conv.create(srcObj, conv
										.getDestination()
										.stream()
										.filter(obj -> obj.getId().equals(srcObj.getId()))
										.findFirst()
										.orElseThrow(() -> new EntityNotFoundException(AbstractAnalyteResult.class)))
							)
					).close();
		return conv.getDestination();
		
	};*/
	
	public LaboratoryTestOrderDtoConfiguration(
			Provider<LaboratoryTest> laboratoryTestProvider,
			Provider<PatientSample> patientSampleProvider,
			Provider<SpecimentType> specimentTypeProvider
			) {
		this.laboratoryTestProvider = laboratoryTestProvider;
		this.patientSampleProvider = patientSampleProvider;
		this.specimentTypeProvider = specimentTypeProvider;
	}
	
	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(LabTestOrder.class, LabTestOrderDTO.class).addMappings( map -> {
			map.map(LabTestOrder::getState, LabTestOrderDTO::setLabTestOrderStatus);
		})
		.includeBase(AbstractAuditableObject.class, AuditableObjectDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(OrderResult.class,SimpleOrderResultDTO.class)
		.includeBase(LabTestOrder.class, LabTestOrderDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(OrderResult.class, OrderResultDTO.class)
		.includeBase(OrderResult.class,SimpleOrderResultDTO.class)
		.implicitMappings();
		
		

		
		mapper.createTypeMap(OrderResult.class, ListOrderResultDTO.class)
		.addMappings(map -> {
			map.map(OrderResult::getState, ListOrderResultDTO::setLabTestOrderStatus);
			map.<ListPatientOrderDTO>map(src -> src.getSample().getOrder(), (dest, v) -> dest.setOrder(v));
		})
		.implicitMappings();
		
		/*tutaj dodane*/
		mapper.createTypeMap(OrderResult.class, OrderResultWithResultListDTO.class)		
		.includeBase(OrderResult.class, ListOrderResultDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(OrderResultWithResultListDTO.class, OrderResult.class)
		.addMappings(map -> {
			map.map(OrderResultWithResultListDTO::getResultDescription, OrderResult::setResultDescription);
			
		});
		mapper.createTypeMap(LabTestOrderDTO.class, LabTestOrder.class, DTOObjectConstans.CREATE.name())
		.addMappings( map ->{
			map.with(laboratoryTestProvider).map(src -> src.getLaboratoryTest().getId(), LabTestOrder<?>::setLaboratoryTest);
			map.map(LabTestOrderDTO::getTatMode, LabTestOrder<?>::setTatMode);
			map.map(LabTestOrderDTO::getResultDescription, LabTestOrder<?>::setResultDescription);
			
		})
		.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class);
		
		mapper.createTypeMap(LabTestOrderDTO.class, LabTestOrder.class, DTOObjectConstans.UPDATE.name())
		.addMappings( map ->{
			map.map(LabTestOrderDTO::getTatMode, LabTestOrder<?>::setTatMode);
			map.map(LabTestOrderDTO::getResultDescription, LabTestOrder<?>::setResultDescription);
			
		})
		.includeBase(AuditableObjectDTO.class, AbstractAuditableObject.class);
		
		
		mapper.createTypeMap(SimpleOrderResultDTO.class, OrderResult.class, DTOObjectConstans.CREATE.name())
		.includeBase(LabTestOrderDTO.class, LabTestOrder.class);
		
		mapper.createTypeMap(SimpleOrderResultDTO.class, OrderResult.class, DTOObjectConstans.UPDATE.name())
		.includeBase(LabTestOrderDTO.class, LabTestOrder.class);
		
		/*mapper.createTypeMap(OrderResultDTO.class, OrderResult.class, DTOObjectConstans.CREATE.name())
		.addMappings(map -> {
			map
			.with(patientSampleProvider)
			.map(src -> src.getSample().getId(), OrderResult::setSample);
		})
		.includeBase(SimpleOrderResultDTO.class, OrderResult.class);*/
		
		mapper.createTypeMap(OrderResultDTO.class, OrderResult.class, DTOObjectConstans.UPDATE.name())
		.includeBase(SimpleOrderResultDTO.class, OrderResult.class);
		
		
		mapper.createTypeMap(LabQualityControlResult.class, SimpleLabQualityControlResultDTO.class)
		.includeBase(LabTestOrder.class, LabTestOrderDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(SimpleLabQualityControlResultDTO.class, LabQualityControlResult.class, DTOObjectConstans.CREATE.name())
		.includeBase(LabTestOrderDTO.class, LabTestOrder.class);
		
		mapper.createTypeMap(SimpleLabQualityControlResultDTO.class, LabQualityControlResult.class, DTOObjectConstans.UPDATE.name())
		.includeBase(LabTestOrderDTO.class, LabTestOrder.class);
		
		
		
		mapper.createTypeMap(LabQualityControlResult.class, LabQualityControlResultDTO.class)
		.includeBase(LabQualityControlResult.class, SimpleLabQualityControlResultDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(LabQualityControlResultDTO.class, LabQualityControlResult.class, DTOObjectConstans.UPDATE.name())
		.includeBase(SimpleLabQualityControlResultDTO.class, LabQualityControlResult.class);
		
		
		
		
		mapper.createTypeMap(LabQualityControlResult.class, LabQualityControlResultWithListDTO.class)
		.<LabQualityControlDTO>addMapping(src -> src.getSample().getOrder(), (dest, v) -> dest.setOrder(v))
		.includeBase(LabQualityControlResult.class, LabQualityControlResultDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(LabQualityControlResultWithListDTO.class, LabQualityControlResult.class)
		.addMappings(map -> {
			map.map(LabQualityControlResultWithListDTO::getResultDescription, LabQualityControlResult::setResultDescription);
			
		});
		
		
		
		
		mapper.createTypeMap(SimplePatientSampleDTO.class, PatientSample.class)
		.addMappings(map -> {
			map
			.with(specimentTypeProvider)
			.map(src -> src.getSpecimentType().getId(), PatientSample::setSpecimentType);
			map.map(SimplePatientSampleDTO::getCollectionDate, PatientSample::setCollectionDate);
			map.map(SimplePatientSampleDTO::getSampleId, PatientSample::setSampleId);
		});
		
		mapper.createTypeMap(PatientSample.class, SimplePatientSampleDTO.class)
		.implicitMappings();
		mapper.createTypeMap(PatientSample.class, PatientSampleDTO.class).implicitMappings();
		
		mapper.createTypeMap(SimpleControlSampleDTO.class, ControlSample.class)
		.addMappings(map -> {
			map
			.with(specimentTypeProvider)
			.map(src -> src.getSpecimentType().getId(), ControlSample::setSpecimentType);
			map.map(SimpleControlSampleDTO::getCollectionDate, ControlSample::setCollectionDate);
			map.map(SimpleControlSampleDTO::getSampleId, ControlSample::setSampleId);
		});
		
		mapper.createTypeMap(ControlSample.class, SimpleControlSampleDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(ControlSample.class, ControlSampleDTO.class).implicitMappings();
		
		mapper.createTypeMap(SpecimentTypeDTO.class, SpecimentType.class)
		.addMappings(map -> {
			map.map(SpecimentTypeDTO::getSpeciment, SpecimentType::setSpeciment);
		});
		
		mapper.createTypeMap(SpecimentType.class, SpecimentTypeDTO.class)
		.implicitMappings();
		
		
		
		
		
		
		
	}
	
	

}
