package base.Controllers.baza1;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.Services.baza1.LaboratoryTestService;
import base.Services.baza1.LaboratoryTestService.SortConstants;
import base.Services.baza1.MethodService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping("lab/laboratoryTests")
public class LaboratoryTestController {

	private LaboratoryTestService laboratoryTestService;
	
	MethodService methodService;
	
	public LaboratoryTestController(LaboratoryTestService laboratoryTestService, MethodService methodService) {
		this.laboratoryTestService = laboratoryTestService;
		this.methodService = methodService;
	}

	@PostMapping(
			value = "/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LaboratoryTestDTO> create(
			@Validated @NotNull @RequestBody LaboratoryTestDTO laboratoryTestDTO
			){
		var laboratoryTest = laboratoryTestService.create(laboratoryTestDTO);
		return ResponseEntity.created(
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.build(laboratoryTest.getId()))
		.body(laboratoryTest);
	}
	
	@PutMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LaboratoryTestDTO> update(
			@PathVariable String id,
			@Validated @NotNull @RequestBody LaboratoryTestDTO laboratoryTestDTO
			) throws EntityNotFoundException {
		var laboratoryTest = laboratoryTestService.update(id, laboratoryTestDTO);
		return ResponseEntity.ok()
				.location(ServletUriComponentsBuilder
				        .fromCurrentRequest()
				        .build()
				        .toUri()
				        )
				.body(laboratoryTest);
	}
	
	@GetMapping(
			value= "/all",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<LaboratoryTestDTO>> getAll(){
		var laboratoryTests = laboratoryTestService.getAll();
		return ResponseEntity
				.ok(laboratoryTests);
	}

	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<LaboratoryTestDTO>> getByExample(
			@RequestParam(required = false) String shortName,
			@RequestParam(required = false) @Size(min=3) String name,
			@RequestParam(required = false) String specimentTypeId,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "NAME") SortConstants sortField,
			@RequestParam(required = true, defaultValue = "ASC") Direction direction
			){
		var laboratoryTests = laboratoryTestService.getAllByExample(shortName, name, specimentTypeId, pageNumber, pageSize, sortField, direction);
		return ResponseEntity
				.ok(laboratoryTests);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LaboratoryTestDTO> getLaboratoryTestById(@PathVariable String id) throws EntityNotFoundException{
		var laboratoryTest = laboratoryTestService.getById(id);
		return ResponseEntity
				.ok()
				.body(laboratoryTest);
	}
	
	@GetMapping(
			value= "/{id}/methods/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<? extends AbstractMethodDTO>> getMethods(
			@PathVariable String id,
			@RequestParam(required = true, defaultValue="true") Boolean onlyActive
			) throws EntityNotFoundException{
		var methods = laboratoryTestService.getMethods(id, onlyActive);
		return ResponseEntity
				.ok()
				.body(methods);
	}
	
	@DeleteMapping(
			value= "/{id}"
			)
	public ResponseEntity<Void> removeLaboratoryTest(
			@PathVariable String id
			) throws EntityNotFoundException{
		//phisicianService.deletePhisicianOrderingUnit(phisicianId, orderingUnitId);
		return ResponseEntity
				.noContent()
				.build();
	}
	
	@PostMapping(
			value = "/{laboratoryTestId}/methods/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public <T extends AbstractMethodDTO> ResponseEntity<T> create(
			@PathVariable String laboratoryTestId,
			@Validated(DTOObjectConstans.Create.class) @NotNull @RequestBody T methodDTO) throws EntityNotFoundException {
		T method = methodService.create(laboratoryTestId, methodDTO);
		return ResponseEntity.created(
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.build(method.getId())
				)
		.body(method);
	}
	

	@PutMapping(
			value= "/{laboratoryTestId}/methods/{methodId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public <T extends AbstractMethodDTO> ResponseEntity<T> update(
			@PathVariable String laboratoryTestId,
			@NotNull @PathVariable String methodId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody T abstractMethodDTO
			){
		T method = methodService.update(laboratoryTestId, methodId, abstractMethodDTO);
		
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(method);
	}
	
	@GetMapping(
			value= "/{laboratoryTestId}/methods/{methodId}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AbstractMethodDTO> getById(
			@PathVariable String laboratoryTestId,
			@PathVariable String methodId
			){
		var method = methodService.getById(laboratoryTestId, methodId);
		return ResponseEntity
				.ok(method);
	}
	
	@DeleteMapping(
			value= "/{laboratoryTestId}/methods/{methodId}"
			)
	public ResponseEntity<Void> deleteById(
			@PathVariable String laboratoryTestId,
			@PathVariable String methodId
			){
		methodService.deleteById(laboratoryTestId, methodId);
		return ResponseEntity
				.noContent()
				.build();
	}
	
	
	
	
	
	
			
}
