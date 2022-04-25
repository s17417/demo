package base.Controllers.baza1;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import base.DTO.baza1.AnalyteDTO.AnalyteDTO;
import base.Services.baza1.AnalyteService;
import base.Services.baza1.AnalyteService.SortConstants;
import base.Utils.Exceptions.EntityNotFoundException;

@RestController
@RequestMapping("/lab/analytes")
public class AnalyteController {

	@Autowired
	private AnalyteService analyteService;
	
	@PostMapping(
			value = "/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AnalyteDTO> create(
			@Validated @NotNull @RequestBody AnalyteDTO analyteDTO
			){
		var analyte = analyteService.createAnalyte(analyteDTO);
		return ResponseEntity.created(
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.build(analyte.getId()))
		.body(analyte);
	}
	
	@PutMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AnalyteDTO> update(
			@PathVariable String id,
			@Validated @NotNull @RequestBody AnalyteDTO analyteDTO
			) throws EntityNotFoundException {
		var analyte = analyteService.updateAnalyte(id, analyteDTO);
		return ResponseEntity.ok()
				.location(ServletUriComponentsBuilder
				        .fromCurrentRequest()
				        .build()
				        .toUri()
				        )
				.body(analyte);
	}
	
	
	@GetMapping(
			value= "/all",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<AnalyteDTO>> getAll(){
		var analytes = analyteService.getAll();
		return ResponseEntity
				.ok(analytes);
	}
	

	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<AnalyteDTO>> getByExample(
			@RequestParam(required = false) String shortName,
			@RequestParam(required = false) @Size(min=3) String name,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "NAME") SortConstants sortField,
			@RequestParam(required = true, defaultValue = "ASC") Direction direction
			){
		var analytes = analyteService.getByExample(
				shortName,
				name,
				pageNumber, 
				pageSize, 
				sortField, 
				direction
				);
		return ResponseEntity
				.ok(analytes);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AnalyteDTO> getAnalyteId(@PathVariable String id) throws EntityNotFoundException{
		var analyte = analyteService.getById(id);
		return ResponseEntity
				.ok()
				.body(analyte);
	}
	
	@DeleteMapping(
			value= "/{id}"
			)
	public ResponseEntity<Void> removeAnalyte(
			@PathVariable String id
			) throws EntityNotFoundException{
		//phisicianService.deletePhisicianOrderingUnit(phisicianId, orderingUnitId);
		return ResponseEntity
				.noContent()
				.build();
	}

}
