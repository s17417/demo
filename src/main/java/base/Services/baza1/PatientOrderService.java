package base.Services.baza1;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultWithResultListDTO;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.OrderingUnit;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.PatientSample;
import base.Repository.Baza1Repository.PatientOrderRepository;
import base.Repository.Baza1Repository.PatientSampleRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class PatientOrderService {
	
	@Autowired
	private PatientOrderRepository patientOrderRepository;
	
	@Autowired
	private PatientSampleRepository patientSampleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public enum SortConstants{
		NAME("patient.name"),
		SURNAME("patient.surname"),
		PERSONAL_ID("patient.personalIdentificationNumber"),
		ORDER_IDENTIFICATION("orderIdentificationCode"),
		ORDER_DATE("orderDate"),
		BIRTHDATE("patient.dateOfBirth"),
		CREATION_DATE("cretionTimeStamp");
		
		private String value;
		
		SortConstants(String value) {
			this.value=value;
		}
		public String getValue() {
			return this.value;
		}
	}
	
	private final ExampleMatcher ordersMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("patient.Id", GenericPropertyMatchers.exact())
			.withMatcher("orderingUnit.Id", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id",
					"orderDate",
					"phisician",
					"patientSamples"		
			);

	
	private final ExampleMatcher patientOrderMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("patient.name", GenericPropertyMatchers.contains())
			.withMatcher("patient.surname", GenericPropertyMatchers.contains())
			.withMatcher("patient.personalIdentificationNumber", GenericPropertyMatchers.exact())
			.withMatcher("orderIdentificationCode", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id",
					"patient.Id",
					"patient.orders",
					"patient.comments",
					"patient.addresses",
					"patient.phoneNumbers",
					"orderDate",
					"phisician",
					"orderingUnit",
					"patientSamples"
					
					);

	@Transactional("laboratoryTransactionManager")
	public PatientOrderDTO createPatientOrder(PatientOrderDTO patientOrderDTO) throws EntityNotFoundException {
		var patientOrder = modelMapper.map(patientOrderDTO, PatientOrder.class);
		patientOrder = patientOrderRepository.saveAndFlush(patientOrder);
		return modelMapper.map(patientOrder, PatientOrderDTO.class);
		
	}
	
	@Transactional("laboratoryTransactionManager")
	public PatientOrderDTO updatePatientOrder(@NotNull String id, @NotNull PatientOrderDTO patientOrderDTO) throws EntityNotFoundException {
		var patientOrder = patientOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));
		modelMapper.map(patientOrderDTO, patientOrder);
		patientOrder = patientOrderRepository.saveAndFlush(patientOrder);
		return modelMapper.map(patientOrder, PatientOrderDTO.class);
	}
	
	
	
	public PatientOrderDTO findPatientOrderById(@NotNull String id) {
		var patientOrder =  patientOrderRepository
				.findPatientOrderById(id)
				.orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));
		return modelMapper.map(patientOrder, PatientOrderDTO.class);	
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public SimplePatientSampleDTO createSample(
			@NotNull String patientOrderId,
			@NotNull SimplePatientSampleDTO patientSampleDTO
			) throws EntityNotFoundException {
		var patientOrder = patientOrderRepository
				.findById(patientOrderId)
				.orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));		
		var patientSample = new PatientSample(patientOrder);
		modelMapper.map(patientSampleDTO, patientSample);
		patientSample = patientSampleRepository.saveAndFlush(patientSample);
		return modelMapper.map(patientSample, SimplePatientSampleDTO.class);
	}
	
	public SimplePatientSampleDTO updateSample(
			@NotNull String patientOrderId,
			@NotNull String patientSampleId,
			@NotNull SimplePatientSampleDTO patientSampleDTO
			) throws EntityNotFoundException {
		var patientSample = patientSampleRepository.findByOrderIdAndSampleId(patientOrderId, patientSampleId)
				.orElseThrow(() -> new EntityNotFoundException(PatientSample.class));
		modelMapper.map(patientSampleDTO, patientSample);
		patientSample = patientSampleRepository.save(patientSample);
		return modelMapper.map(patientSample, SimplePatientSampleDTO.class);
	}
	
	public void removeSample(
			@NotNull String patientOrderId,
			@NotNull String patientSampleId
			) throws EntityNotFoundException {
		var patientSample = patientSampleRepository
				.findByIdAndOrderIdWithLabTestOrders(patientOrderId, patientSampleId)
				.orElseThrow(() -> new EntityNotFoundException(PatientSample.class));
		if (patientSample.getLabTestOrders().isEmpty()) 
			patientSampleRepository.delete(patientSample);
	}
	
	public List<SimplePatientSampleDTO> getSamples(
			@NotNull String patientOrderId
			) throws EntityNotFoundException {
		var patientSamples = patientSampleRepository.findAllByOrderId(patientOrderId);
		return patientSamples
				.stream()
				.map(obj -> modelMapper.map(obj, SimplePatientSampleDTO.class))
				.collect(Collectors.toList());
	}
	
	
	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public Page<ListPatientOrderDTO> findAllPatientOrders(
			String name, 
			String surname, 
			String personalIdentificationNumber, 
			String orderIdentification,
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			LocalDate fromOrderDate,
			LocalDate toOrderDate,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			) {
		var patient = new Patient();
		patient.setName(name);
		patient.setSurname(surname);
		patient.setPersonalIdentificationNumber(personalIdentificationNumber);
		var patientOrder = new PatientOrder(patient);
		patientOrder.setOrderIdentificationCode(orderIdentification);
		System.out.println(patientOrder);
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				 Sort.by(direction,sortField.getValue())
				);
		return 
				patientOrderRepository
				.findAll(getSpecificationDatesExample(dateOfBirthFrom,dateOfBirthTo,fromOrderDate, toOrderDate, Example.of(patientOrder, patientOrderMatcher)), page)
				.map(obj -> modelMapper.map(obj, ListPatientOrderDTO.class));	
	}
	
	@Transactional
	public Page<ListPatientOrderDTO> findAllOrderingUnitAndPatientOrders(
			String orderingUnitId,
			String patientId,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			){
		
		var patient = new Patient();
		patient.setId(patientId);
		var patientOrder = new PatientOrder(patient);
		patientOrder.setId(null);
		if (orderingUnitId!=null) {
			var orderingUnit = new OrderingUnit();
			orderingUnit.setId(orderingUnitId);
			patientOrder.setOrderingUnit(orderingUnit);
		}
		return patientOrderRepository.findAll(
				getSpecificationExample(
						Example.of(patientOrder, ordersMatcher)
						),
				PageRequest.of(
						pageNumber,
						pageSize,
						Sort.by(
							direction,
							sortField.getValue()
						)
				)
		).map(obj -> modelMapper.map(obj, ListPatientOrderDTO.class));
	}
	
	
	private Specification<PatientOrder> getSpecificationDatesExample(
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			LocalDate orderDateFrom,
			LocalDate orderDateTo,
			Example<PatientOrder> exmpl){
		return (root, query, builder) ->{
			List<Predicate> predicates = new ArrayList<>();
			if (dateOfBirthFrom!=null) predicates.add(builder.greaterThanOrEqualTo(root.join("patient").get("dateOfBirth"), dateOfBirthFrom));
			if (dateOfBirthTo!=null) predicates.add(builder.lessThanOrEqualTo(root.join("patient").get("dateOfBirth"), dateOfBirthTo));
			if (orderDateFrom!=null) predicates.add(builder.greaterThanOrEqualTo(root.get("orderDate"), orderDateFrom));
			if (orderDateTo!=null) predicates.add(builder.lessThanOrEqualTo(root.get("orderDate"), orderDateTo));
			predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, exmpl));
			if (Long.class != query.getResultType()) {
				root.fetch("patient", JoinType.LEFT);
				root.fetch("orderingUnit",JoinType.LEFT);
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		
	}
	
	private Specification<PatientOrder> getSpecificationExample(
			Example<PatientOrder> exmpl){
		return (root, query, builder) ->{
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, exmpl));
			if (Long.class != query.getResultType()) {
				root.fetch("patient", JoinType.LEFT);
				root.fetch("orderingUnit",JoinType.LEFT);
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		
	}

}
