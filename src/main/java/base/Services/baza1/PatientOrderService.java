package base.Services.baza1;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import base.DTO.baza1.PatientOrderDTO.PatientOrderDTO;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Repository.Baza1Repository.PatientOrderRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class PatientOrderService {
	
	@Autowired
	private PatientOrderRepository patientOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final ExampleMatcher patientOrderMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("patient.name", GenericPropertyMatchers.contains())
			.withMatcher("patient.surname", GenericPropertyMatchers.contains())
			.withMatcher("patient.personalIdentificationNumber", GenericPropertyMatchers.exact())
			.withMatcher("orderIdentification", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id","patient.Id","orderDate");

	@Transactional("laboratoryTransactionManager")
	public PatientOrderDTO createPatientOrder(PatientOrderDTO patientOrderDTO) throws EntityNotFoundException {
		var patientOrder = modelMapper.map(patientOrderDTO, PatientOrder.class);
		patientOrder = patientOrderRepository.saveAndFlush(patientOrder);
		return modelMapper.map(patientOrder, PatientOrderDTO.class);
		
	}
	
	@Transactional
	public PatientOrderDTO updatePatientOrder(@NotNull String id, @NotNull PatientOrderDTO patientOrderDTO) throws EntityNotFoundException {
		var patientOrder = patientOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));
		modelMapper.map(patientOrderDTO, patientOrder);
		patientOrder = patientOrderRepository.saveAndFlush(patientOrder);
		return modelMapper.map(patientOrder, PatientOrderDTO.class);
	}
	
	public PatientOrderDTO findPatientOrderById(@NotNull String id) {
		var patientOrder =  patientOrderRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));
		return modelMapper.map(patientOrder, PatientOrderDTO.class);	
	}
	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public List<PatientOrderDTO> findAllPatientOrders(
			String name, 
			String surname, 
			String personalIdentificationNumber, 
			String orderIdentification,
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			LocalDate fromOrderDate,
			LocalDate toOrderDate
			) {
		var patient = new Patient();
		patient.setName(name);
		patient.setSurname(surname);
		patient.setPersonalIdentificationNumber(personalIdentificationNumber);
		var patientOrder = new PatientOrder(patient);
		patientOrder.setOrderIdentification(orderIdentification);
		var patientOrderExample = Example.of(patientOrder, patientOrderMatcher);
		return 
				patientOrderRepository
				.findAll(getSpecificationDatesExample(dateOfBirthFrom,dateOfBirthTo,fromOrderDate, toOrderDate, patientOrderExample))
				.stream()
				.map(obj -> modelMapper.map(obj, PatientOrderDTO.class))
				.collect(Collectors.toList());			
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
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		
	}

}
