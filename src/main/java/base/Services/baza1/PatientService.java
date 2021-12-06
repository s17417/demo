package base.Services.baza1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.NotNull;

import base.DTO.baza1.CommentDTO;
import base.DTO.baza1.PatientDTO.SimplePatientWithCollectionsDTO;
import base.DTO.baza1.PatientOrderDTO.PatientOrderDTO;
import base.DTO.baza1.PatientDTO.SimplePatientDTO;
import base.Model.baza1.Patient;
import base.Repository.Baza1Repository.PatientRepository;
import base.Utils.Exceptions.EntityNotFoundException;



@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final ExampleMatcher patientMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("surname", GenericPropertyMatchers.contains())
			.withMatcher("personalIdentificationNumber", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id","addresses","comments","phoneNumbers","gender","patientOrders","dateOfBirth");
	
	@Transactional("laboratoryTransactionManager")
	public SimplePatientWithCollectionsDTO createPatient(@NotNull SimplePatientWithCollectionsDTO patientDTO) {
		var savedObject = patientRepository.saveAndFlush(modelMapper.map(patientDTO, Patient.class/*, DTOObjectConstans.CREATE.name()*/));
		return modelMapper.map(savedObject, SimplePatientWithCollectionsDTO.class);
		
	}

	@Transactional("laboratoryTransactionManager")
	public SimplePatientWithCollectionsDTO updatePatient (@NotNull String id, @NotNull SimplePatientWithCollectionsDTO patientDTO) throws EntityNotFoundException {
		var patient = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Patient.class));
		modelMapper.map(patientDTO, patient);
		patientRepository.saveAndFlush(patient);
		return modelMapper.map(patient,SimplePatientWithCollectionsDTO.class);
	}

	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public SimplePatientWithCollectionsDTO findPatientById(@NotNull String Id){
		var patient = patientRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException(Patient.class));
		return modelMapper.map(patient, SimplePatientWithCollectionsDTO.class);	
	}
	
	public List<SimplePatientDTO> findPatientsByExample(
			String name,
			String surname,
			LocalDate dateOfBirthFrom,
			LocalDate dateOfBirthTo,
			String personalIdentificationNumber){
		var patient = new Patient();
		patient.setName(name);
		patient.setSurname(surname);
		patient.setPersonalIdentificationNumber(personalIdentificationNumber);
		return patientRepository
				.findAll(this.getSpecificationDatesExample(dateOfBirthFrom, dateOfBirthTo, Example.of(patient, patientMatcher)))
				.stream()
				.map(obj -> modelMapper.map(obj, SimplePatientDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public List<CommentDTO> getPatientComments(@NotNull String patientId) throws EntityNotFoundException{
		return patientRepository
				.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException(Patient.class))
				.getComments()
				.stream()
				.map(obj -> modelMapper.map(obj, CommentDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional("laboratoryTransactionManager")
	public void addPatientComments(@NotNull String patientId, @NotNull List<String> comments) throws EntityNotFoundException {
		var patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException(Patient.class));
		comments
		.stream()
		.map(comment -> patient.addComment(comment))
		.collect(Collectors.toList());
	}
	
	@Transactional("laboratoryTransactionManager")
	public List<PatientOrderDTO> getPatientPatientOrders(@NotNull String patientId) throws EntityNotFoundException{
		return patientRepository
				.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException(Patient.class))
				.getPatientOrders()
				.stream()
				.map(obj -> modelMapper.map(obj, PatientOrderDTO.class))
				.collect(Collectors.toList());
	}
	
	public Specification<Patient> getSpecificationDatesExample(
			LocalDate from,
			LocalDate to, 
			Example<Patient> exmpl){
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (from!=null) predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"), from));
			if (to!=null) predicates.add(builder.lessThanOrEqualTo(root.get("dateOfBirth"), to));
			predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, exmpl));
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	
}
