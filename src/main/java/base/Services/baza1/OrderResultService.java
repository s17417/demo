package base.Services.baza1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.labTestOrderDTO.ListOrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleOrderResultDTO;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.OrderResult;
import base.Model.baza1.OrderStatus;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.PatientSample;
import base.Model.baza1.TatMode;
import base.Repository.Baza1Repository.OrderResultRepository;
import base.Repository.Baza1Repository.PatientOrderRepository;
import base.Repository.Baza1Repository.PatientSampleRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class OrderResultService {
	
	public enum SortConstants{
		NAME("order.patient.name"),
		SURNAME("order.patient.surname"),
		PERSONAL_ID("order.patient.personalIdentificationNumber"),
		ORDER_IDENTIFICATION("order.orderIdentification"),
		ORDER_DATE("order.orderDate");
		
		private String value;
		
		SortConstants(String value) {
			this.value=value;
		}
		public String getValue() {
			return this.value;
		}
	}
	
	//private EntityManager entityManager;
	
	private PatientOrderRepository patientOrderRepository;
	
	private PatientSampleRepository patientSampleRepository;
	
	private OrderResultRepository orderResultRepository;
	
	private ModelMapper modelMapper;


	public OrderResultService(/*EntityManager entityManager, */PatientOrderRepository patientOrderRepository,
			PatientSampleRepository patientSampleRepository, OrderResultRepository orderResultRepository,
			ModelMapper modelMapper) {
		//this.entityManager = entityManager;
		this.patientOrderRepository = patientOrderRepository;
		this.patientSampleRepository = patientSampleRepository;
		this.orderResultRepository = orderResultRepository;
		this.modelMapper = modelMapper;
	}


	private final ExampleMatcher orderResultMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("tatMode", GenericPropertyMatchers.contains())
			.withMatcher("labTestOrderStatus", GenericPropertyMatchers.contains())
			.withMatcher("order.patient.name", GenericPropertyMatchers.contains())
			.withMatcher("order.patient.surname", GenericPropertyMatchers.contains())
			.withMatcher("order.patient.personalIdentificationNumber", GenericPropertyMatchers.exact())
			.withMatcher("order.orderIdentificationCode", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id","order.Id","order.patient.Id","order.orderDate");

	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultDTO create(@NotNull String patientOrderId, @NotNull String patientSampleId, @NotNull SimpleOrderResultDTO orderResultDTO)throws EntityNotFoundException{
		var patientSample = patientSampleRepository
				.findByOrderIdAndSampleId(patientOrderId, patientSampleId)
				.orElseThrow(() -> new EntityNotFoundException(PatientSample.class));
		
		var orderResult = modelMapper.map(orderResultDTO, OrderResult.class, DTOObjectConstans.CREATE.name());
		orderResult.setSample(patientSample);
		orderResult.createAnalyteResults();

		var obj=orderResultRepository.saveAndFlush(orderResult);
		return modelMapper
				.map(obj, OrderResultDTO.class);
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultDTO cancelOrder(@NotNull String patientOrderId,  @NotNull String patientSampleId, @NotNull String orderResultId) {
		var orderResult = orderResultRepository
				.findByIdAndOrder(patientOrderId, patientSampleId, orderResultId)
				.orElseThrow(() -> new EntityNotFoundException(OrderResult.class));	
		orderResult.setState(OrderStatus.CANCELLED);
		return modelMapper
				.map(orderResultRepository.saveAndFlush(orderResult), OrderResultDTO.class);
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultDTO update(@NotNull String patientOrderId,  @NotNull String patientSampleId, @NotNull String orderResultId, @NotNull SimpleOrderResultDTO orderResultDTO)throws EntityNotFoundException{
		var orderResult = orderResultRepository
				.findByIdAndOrder(patientOrderId, patientSampleId, orderResultId)
				.orElseThrow(() -> new EntityNotFoundException(OrderResult.class));		
		
		modelMapper.map(orderResultDTO, orderResult, DTOObjectConstans.UPDATE.name());
		if(orderResult.getState().equals(OrderStatus.PENDING)||orderResult.getState().equals(OrderStatus.CANCELLED))
			orderResult.setState(OrderStatus.PENDING);
		else orderResult.setState(OrderStatus.PROCESSED);
		orderResultRepository.save(orderResult);
		
		return modelMapper
				.map(orderResultRepository.saveAndFlush(orderResult), OrderResultDTO.class);	
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultDTO getById(
			@NotNull String patientOrderId,
			@NotNull String patientSampleId,
			@NotNull String orderResultId
			)throws EntityNotFoundException{
		return orderResultRepository
				.findByIdAndOrder(patientOrderId, patientSampleId, orderResultId)
				.map(obj -> modelMapper.map(obj, OrderResultDTO.class))
				.orElseThrow(() -> new EntityNotFoundException(OrderResult.class));				
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public List<OrderResultDTO> getPatientOrderResults(@NotNull String patientOrderId) {
		//var r = AuditReaderFactory.get(entityManager).createQuery().forRevisionsOfEntity(LaboratoryTest.class, true,true);
		
				return orderResultRepository
				.findAllByOrderId(patientOrderId)
				.stream()
				.map(obj -> modelMapper.map(obj, OrderResultDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public Page<ListOrderResultDTO> getAllOrderResults(
			String name, 
			String surname, 
			String personalIdentificationNumber, 
			String orderIdentification,
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			LocalDate fromOrderDate,
			LocalDate toOrderDate,
			TatMode tatMode,
			OrderStatus labTestOrderStatus,
			String methodId,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			){
		
		var patient = new Patient();
		patient.setName(name);
		patient.setSurname(surname);
		patient.setPersonalIdentificationNumber(personalIdentificationNumber);
		
		var patientOrder = new PatientOrder(patient);
		patientOrder.setOrderIdentificationCode(orderIdentification);
		
		var patientSample = new PatientSample(patientOrder);
		
		var orderResult = new OrderResult();
		orderResult.setSample(patientSample);
		orderResult.setTatMode(tatMode);
		orderResult.setState(labTestOrderStatus);
		
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				 Sort.by(direction,sortField.getValue())
				);
		
		return orderResultRepository
				.findAll(getSpecificationDatesExample(
						dateOfBirthFrom,
						dateOfBirthTo,
						fromOrderDate,
						toOrderDate,
						Example.of(orderResult, orderResultMatcher)),
						page)
				.map(obj -> modelMapper.map(obj, ListOrderResultDTO.class));
	}
	
	
	private Specification<OrderResult> getSpecificationDatesExample(
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			LocalDate orderDateFrom,
			LocalDate orderDateTo,
			Example<OrderResult> exmpl){
		return (root, query, builder) ->{
			List<Predicate> predicates = new ArrayList<>();
			if (dateOfBirthFrom!=null) predicates.add(builder.greaterThanOrEqualTo(root.join("sample").join("order").join("patient").get("dateOfBirth"), dateOfBirthFrom));
			if (dateOfBirthTo!=null) predicates.add(builder.lessThanOrEqualTo(root.join("sample").join("order").join("patient").get("dateOfBirth"), dateOfBirthTo));
			if (orderDateFrom!=null) predicates.add(builder.greaterThanOrEqualTo(root.join("sample").join("order").get("orderDate"), orderDateFrom));
			if (orderDateTo!=null) predicates.add(builder.lessThanOrEqualTo(root.join("sample").join("order").get("orderDate"), orderDateTo));
			predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, exmpl));
			if (Long.class != query.getResultType()) {
				root.fetch("laboratoryTest");
				var order=root.fetch("sample").fetch("order");
				order.fetch("patient");
				order.fetch("orderingUnit", JoinType.LEFT);
				//powinienenm dodac do wszystkich JoinType.LEFT inaczej bedzie wybierac sumarycznie wartosci nie null
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		
	}
	
}
