package base.Controllers.baza1;

import java.util.List;

import javax.validation.constraints.NotNull;
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

import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.Services.baza1.LabQualityControlService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/labQualityControls")
public class LabQualityControlController {

	private LabQualityControlService labQualityControlService;

	public LabQualityControlController(LabQualityControlService labQualityControlService) {
		this.labQualityControlService = labQualityControlService;
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
			value= "/",
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
	public ResponseEntity<List<LabQualityControlDTO>> getByExample(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String externalIdentificationCode,
			@RequestParam(required = false) String orderIdentification
			){
		var labQualityControls = labQualityControlService.findByExample(
				name,
				externalIdentificationCode,
				orderIdentification);
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
	
	

}
