package base.Controllers.baza1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import base.DTO.baza1.PhisicianDTO.PhisicianDTO;
import base.Services.baza1.PhisicianService;
import base.Services.baza1.PhisicianService.SortConstantsPhisician;
import base.Utils.Exceptions.EntityNotFoundException;

@CrossOrigin
@Validated
@RestController
@RequestMapping(value="/lab/phisicians")
public class PhisicianController {

	@Autowired
	private PhisicianService phisicianService;
	
	@PostMapping(
			value="/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PhisicianDTO> createPhisician(
			@Validated(DTOObjectConstans.Create.class) @RequestBody PhisicianDTO phisicianDTO
			){
		var phisician = phisicianService.createPhisician(phisicianDTO);
		return ResponseEntity
				.created(ServletUriComponentsBuilder
				        .fromCurrentRequest()
				        .path("/{id}")
				        .build(phisician.getId())
				        )
				.body(phisician);		
	}
	
	@PutMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PhisicianDTO> updatePhisician(
			@PathVariable String id,
			@Validated(DTOObjectConstans.Update.class) @RequestBody PhisicianDTO phisiciantDTO
			) throws EntityNotFoundException {
		var phisician = phisicianService.updatePhisician(id, phisiciantDTO);
		return ResponseEntity.ok()
				.location(ServletUriComponentsBuilder
				        .fromCurrentRequest()
				        .build()
				        .toUri()
				        )
				.body(phisician);
	}
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<PhisicianDTO>> getPhisiciansByExample(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,
			@RequestParam(required = false) String personalIdentificationNumber,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "SURNAME") SortConstantsPhisician sortField,
			@RequestParam(required = true, defaultValue = "ASC") Direction direction
			){
		var phisicians = phisicianService.getPhisicianByExample(
				name,
				surname,
				personalIdentificationNumber,
				pageNumber,
				pageSize,
				sortField,
				direction
				);
		return ResponseEntity
				.ok(phisicians);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PhisicianDTO> getPhisiciansById(@PathVariable String id) throws EntityNotFoundException{
		var phisician = phisicianService.getPhisicianById(id);	
		return ResponseEntity
				.ok()
				.body(phisician);
	}
	
	@PutMapping(
			value= "/{phisicianId}/orderingUnits/{orderingUnitId}"
			)
	public ResponseEntity<Void> addPhisicianOderingUnits(
			@PathVariable String phisicianId,
			@PathVariable String orderingUnitId
			) throws EntityNotFoundException {
		phisicianService.addPhisicianOrderingUnits(phisicianId, orderingUnitId);
		return ResponseEntity.ok()
				.location(ServletUriComponentsBuilder
				        .fromCurrentRequest()
				        .build()
				        .toUri())
				.build();
	}
	
	@DeleteMapping(
			value= "/{phisicianId}/orderingUnits/{orderingUnitId}"
			)
	public ResponseEntity<Void> removePhisicianOrderingUnits(
			@PathVariable String phisicianId,
			@PathVariable String orderingUnitId
			) throws EntityNotFoundException{
		phisicianService.deletePhisicianOrderingUnit(phisicianId, orderingUnitId);
		return ResponseEntity
				.noContent()
				.build();
	}
	
	@GetMapping(
			value= "/{id}/orderingUnits",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Object> getPhisicianOrderingUnits(@PathVariable String id) throws EntityNotFoundException{
		var orderingUnits = phisicianService.getOrderingUnits(id);
		return ResponseEntity
				.ok()
				.body(orderingUnits);
	}
	
	

}
