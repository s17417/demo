package base.Controllers.baza1;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
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
import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.DTO.baza1.PatientSampleDTO.SimpleControlSampleDTO;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultDTO;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultWithListDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleLabQualityControlResultDTO;
import base.Services.baza1.AnalyteResultService;
import base.Services.baza1.LabQualityControlResultService;
import base.Services.baza1.LabQualityControlService;
import base.Services.baza1.LabQualityControlService.SortConstants;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/labQualityControls")
public class LabQualityControlController {

	private LabQualityControlService labQualityControlService;
	
	private LabQualityControlResultService labQualityControlResultService;
	
	private AnalyteResultService analyteResultService;
	
	public LabQualityControlController(LabQualityControlService labQualityControlService,
			LabQualityControlResultService labQualityControlResultService, AnalyteResultService analyteResultService) {
		this.labQualityControlService = labQualityControlService;
		this.labQualityControlResultService = labQualityControlResultService;
		this.analyteResultService = analyteResultService;
	}

	@PostMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlDTO> create(
			@Validated @RequestBody LabQualityControlDTO labQualityControlDTO
			) {
		var labQualityControl = labQualityControlService.create(labQualityControlDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(labQualityControl.getId()))
				.body(labQualityControl);
	}
	
	@PutMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlDTO> update(
			@NotNull @PathVariable String id,
			@Validated @RequestBody LabQualityControlDTO labQualityControlDTO
			) throws EntityNotFoundException {
		var labQualityControl = labQualityControlService.update(id, labQualityControlDTO);
		
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(labQualityControl);
	}
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<LabQualityControlDTO>> getByExample(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String externalIdentificationCode,
			@RequestParam(required = false) String orderIdentification,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "CREATION_DATE") SortConstants sortField,
			@RequestParam(required = true, defaultValue = "DESC") Direction direction
			){
		
		var labQualityControls = labQualityControlService.findByExample(
				name,
				externalIdentificationCode,
				orderIdentification,
				pageNumber,
				pageSize,
				sortField,
				direction);
		return ResponseEntity
				.ok(labQualityControls);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlDTO> getById(
			@PathVariable String id
			){
		var labQualityControl = labQualityControlService.getById(id);
		return ResponseEntity
				.ok(labQualityControl);
	}
	
	/**
	 * Creates new control sample
	 * @param controlOrderId
	 * @param controlSampleDTO
	 * @return
	 * @throws EntityNotFoundException
	 */
	@PostMapping(
			value= "/{controlOrderId}/controlSamples/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimpleControlSampleDTO> createControlSample(
			@PathVariable String controlOrderId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimpleControlSampleDTO controlSampleDTO
			) throws EntityNotFoundException {
		var controlSample = labQualityControlService.createSample(controlOrderId, controlSampleDTO);
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{controlOrderId}/patientSamples/{id}")
						.build(controlOrderId, controlSample.getId()))
				.body(controlSample);
	}
	
	
	/**
	 * Retrive all spamples attached to LabQualityControl
	 * @param controlOrderId
	 * @return
	 */
	@GetMapping(
			value= "/{controlOrderId}/controlSamples/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SimpleControlSampleDTO>> getControlOrderSamples(@PathVariable String controlOrderId){
		var controlSamples = labQualityControlService.getSamples(controlOrderId);
		return ResponseEntity
				.ok(controlSamples);		
	}	
	
	@PostMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultDTO> createControlResult(
			@PathVariable String controlOrderId,
			@PathVariable String controlSampleId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimpleLabQualityControlResultDTO controlResultDTO
			) throws EntityNotFoundException {
		var controlResult = labQualityControlResultService.create(controlOrderId,controlSampleId,controlResultDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(controlResult.getId()))
				.body(controlResult);
	}
	
	//do zmiany
	@PutMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/{controlResultId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultDTO> updateControlResult(
			@PathVariable String controlResultId,
			@PathVariable String controlSampleId,			
			@PathVariable String controlOrderId,
			@Validated @RequestBody SimpleLabQualityControlResultDTO controlResultDTO
			) throws EntityNotFoundException {
		var controlResult = labQualityControlResultService.update(controlOrderId, controlSampleId, controlResultId, controlResultDTO);	
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(controlResult);
	}
	
	@GetMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/{controlResultId}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultDTO> getControlResults(
			@PathVariable String controlOrderId,
			@PathVariable String controlSampleId,
			@PathVariable String controlResultId
			) throws EntityNotFoundException {
		var controlResult = labQualityControlResultService.getById(controlOrderId,controlSampleId,controlResultId);
		return ResponseEntity
				.ok(controlResult);
	}
	
	/**
	 * Get all all control results
	 * @param controlOrderId
	 * @return
	 * @throws EntityNotFoundException
	 */
	@GetMapping(
			value= "/{controlOrderId}/controlSamples/controlResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<LabQualityControlResultDTO>> getControlResult(
			@PathVariable String controlOrderId
			)throws EntityNotFoundException {
		List<LabQualityControlResultDTO> controlResults = labQualityControlResultService.getControlOrderResults(controlOrderId);
		return ResponseEntity
				.ok(controlResults);
	}
	
	@PutMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/{controlResultId}/cancel",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultDTO> cancelControlResult(
			@PathVariable String controlResultId,
			@PathVariable String controlSampleId,			
			@PathVariable String controlOrderId
			) throws EntityNotFoundException {
		var controlResult = labQualityControlResultService.cancelOrder(controlOrderId, controlSampleId, controlResultId);	
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(controlResult);
	}
	
	@GetMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/{controlResultId}/analyteResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultWithListDTO> getAnalyteResultsWithControlOrder(
			@PathVariable String controlOrderId,
			@PathVariable String controlSampleId,
			@PathVariable String controlResultId
			)throws EntityNotFoundException {
		var analyteResults = analyteResultService.getAllWithControlOrder(controlOrderId, controlSampleId, controlResultId);
		System.out.println(analyteResults.getAnalyteResults());
		return ResponseEntity
				.ok(analyteResults);
	}
	
	@PutMapping(
			value= "/{controlOrderId}/controlSamples/{controlSampleId}/controlResults/{controlResultId}/analyteResults/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LabQualityControlResultWithListDTO> updateResult(
			@PathVariable String controlOrderId,
			@PathVariable String controlSampleId,
			@PathVariable String controlResultId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody LabQualityControlResultWithListDTO controlResultDTO
			) throws EntityNotFoundException {
		
		var result = analyteResultService.updateControlResult(controlOrderId, controlSampleId, controlResultId, controlResultDTO);
		//var orderResult = orderResultService.create(patientOrderId,patientSampleId,orderResultDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(result.getId()))
				.body(result);
	}
	

}
