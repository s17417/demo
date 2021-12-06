package base.Controllers.baza1;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

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
import base.Services.baza1.PatientOrderService;
import base.Services.baza1.PatientService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/patientOrders")
public class PatientOrderController {

	@Autowired
	PatientOrderService patientOrderService;
	
	@PostMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PatientOrderDTO> createPatientOrder(
			@Validated(DTOObjectConstans.Create.class) @RequestBody PatientOrderDTO patientOrderDTO
			) throws EntityNotFoundException {
		var patientOrder = patientOrderService.createPatientOrder(patientOrderDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(patientOrder.getId()))
				.body(patientOrder);
	}
	
	@PutMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PatientOrderDTO> updatePatientOrder(
			@NotNull @PathVariable String id,
			@Validated(DTOObjectConstans.Create.class) @RequestBody PatientOrderDTO patientOrderDTO
			){
		var patientOrder = patientOrderService.updatePatientOrder(id, patientOrderDTO);
		
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(patientOrder);
	}
	
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<PatientOrderDTO>> getAllPatientOrders(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,
			@RequestParam(required = false) String personalIdentificationNumber,
			@RequestParam(required = false) String orderIdentification,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromOrderDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toOrderDate
			){
		var patientOrder = patientOrderService.findAllPatientOrders(
				name,
				surname,
				personalIdentificationNumber,
				orderIdentification,
				fromBirthDate,
				toBirthDate,
				fromOrderDate,
				toOrderDate);
		return ResponseEntity
				.ok(patientOrder);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PatientOrderDTO> getPatientOrdersById(
			@PathVariable String id
			){
		var patientOrder = patientOrderService.findPatientOrderById(id);
		return ResponseEntity
				.ok(patientOrder);
	}
	

}
