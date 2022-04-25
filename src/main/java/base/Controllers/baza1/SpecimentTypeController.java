package base.Controllers.baza1;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.PatientSampleDTO.SpecimentTypeDTO;
import base.Services.baza1.SpecimentTypeService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/specimentTypes")
public class SpecimentTypeController {
	
	private SpecimentTypeService specimentTypeService;
	
	public SpecimentTypeController(SpecimentTypeService specimentTypeService) {
		this.specimentTypeService = specimentTypeService;
	}

	@PostMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SpecimentTypeDTO> create(
			@RequestBody SpecimentTypeDTO specimentTypeDTO
			) {
		var speciment = specimentTypeService.create(specimentTypeDTO);
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(speciment.getId()))
				.body(speciment);
	}
	
	@PutMapping(
			value= "/{patientSampleId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SpecimentTypeDTO> update(
			@PathVariable String specimentTypeId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SpecimentTypeDTO specimentTypeDTO
			) throws EntityNotFoundException {
		var speciment = specimentTypeService.update(specimentTypeId, specimentTypeDTO);
		return ResponseEntity
				.ok(speciment);
	}
	
	@GetMapping(
			value= "/{patientSampleId}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SpecimentTypeDTO> getById(@PathVariable String specimentTypeId){
		var speciment = specimentTypeService.getById(specimentTypeId);
		return ResponseEntity
				.ok(speciment);		
	}
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SpecimentTypeDTO>> getAll(){
		var speciments = specimentTypeService.getAll();
		return ResponseEntity
				.ok(speciments);		
	}	
	
	@DeleteMapping(
			value= "/{patientSampleId}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Void> delete(@PathVariable String specimentTypeId){
		specimentTypeService.setActiveStatus(specimentTypeId, false);
		return ResponseEntity
				.noContent()
				.build();	
	}
	
	

}
