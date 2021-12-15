package base.Controllers.baza1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.DTO.baza1.MethodDTO.QuantitativeFormatMethodDTO;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.Model.baza1.QuantitativeFormatMethod;
import base.Services.baza1.MethodService;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping("lab/methods")
public class MethodController {

	MethodService methodService;

	@Autowired
	public MethodController(MethodService methodService) {
		this.methodService = methodService;
	}
	
	/*@PostMapping(
			value = "/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public <T extends AbstractMethodDTO> ResponseEntity<T> create(
			@Validated(DTOObjectConstans.Create.class) @NotNull @RequestBody T methodDTO
			) throws EntityNotFoundException {
		T method = methodService.create(methodDTO);
		return ResponseEntity.created(
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.build(method.getId())
				)
		.body(method);
	}
	

	@PutMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public <T extends AbstractMethodDTO> ResponseEntity<T> update(
			@NotNull @PathVariable String id,
			@Validated(DTOObjectConstans.Create.class) @RequestBody T abstractMethodDTO
			){
		T method = methodService.update(id, abstractMethodDTO);
		
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(method);
	}
	
	@GetMapping(
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AbstractMethodDTO> getById(
			@PathVariable String id
			){
		var method = methodService.getById(id);
		return ResponseEntity
				.ok(method);
	}
	
	@DeleteMapping(
			value= "/{id}"
			)
	public ResponseEntity<Void> deleteById(
			@PathVariable String id
			){
		methodService.deleteById(id);
		return ResponseEntity
				.noContent()
				.build();
	}
	*/
}
