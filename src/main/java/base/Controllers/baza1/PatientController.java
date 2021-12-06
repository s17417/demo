package base.Controllers.baza1;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.PatientDTO.SimplePatientWithCollectionsDTO;
import base.DTO.baza1.PatientOrderDTO.PatientOrderDTO;
import base.DTO.baza1.CommentDTO;
import base.DTO.baza1.PatientDTO.SimplePatientDTO;
import base.Services.baza1.PatientService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/patients")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@PostMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimplePatientWithCollectionsDTO> createPatient(
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimplePatientWithCollectionsDTO patientDTO
			){
		var patient = patientService.createPatient(patientDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(patient.getId()))
				.body(patient);
	}
	
	@PutMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimplePatientWithCollectionsDTO> updatePatient(
			@PathVariable String id,
			@Validated(DTOObjectConstans.Update.class) @RequestBody SimplePatientWithCollectionsDTO patientDTO
			) throws EntityNotFoundException{
		var patient = patientService.updatePatient(id, patientDTO);
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(patient);
	}
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SimplePatientDTO>> getPatientsByExample(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirthFrom,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirthTo,
			@RequestParam(required = false) String personalIdentificationNumber
			){
		var patients = patientService.findPatientsByExample(name, surname, dateOfBirthFrom, dateOfBirthTo, personalIdentificationNumber);		
		return  ResponseEntity
				.ok(patients);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimplePatientWithCollectionsDTO> getPatientsById(
			@PathVariable String id
			){
		var patient = patientService.findPatientById(id);
		return ResponseEntity
				.ok(patient);
	}
	
	@GetMapping(
			value= "/{patientId}/patientComments",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<CommentDTO>> getPatientComments(@PathVariable String patientId) throws EntityNotFoundException{
		var comments = patientService.getPatientComments(patientId);
		return ResponseEntity
				.ok(comments);	
	}
	
	@PostMapping(
			value= "/{patientId}/patientComments",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Void> createPatientComment(@PathVariable String patientId, @NotBlank List<String> patientComment) throws EntityNotFoundException{
		patientService.addPatientComments(patientId, patientComment);
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(patientId))
				.build();		
	}
	
	@GetMapping(
			value= "/{patientId}/patientOrders",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<PatientOrderDTO>>  getPatientOrders (@PathVariable String patientId) throws EntityNotFoundException{
		var patientOrders = patientService.getPatientPatientOrders(patientId);
		return ResponseEntity
				.ok(patientOrders);
	
	}

}
