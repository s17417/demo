package base.Controllers.baza1;

import java.util.List;

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

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.OrderingUnitDTO.OrderingUnitDTO;
import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;
import base.DTO.baza1.PhisicianDTO.PhisicianDTO;
import base.Services.baza1.OrderingUnitService;
import base.Services.baza1.OrderingUnitService.SortConstantsOrderingUnit;
import base.Services.baza1.PatientOrderService;
import base.Services.baza1.PatientOrderService.SortConstants;
import base.Services.baza1.PhisicianService.SortConstantsPhisician;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/orderingUnits")
public class OrderingUnitController {

	@Autowired
	private OrderingUnitService orderingUnitService;
	@Autowired
	private PatientOrderService patientOrderService;
	
	@PostMapping(
			value= "/",
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderingUnitDTO> createOrderingUnit(
			@Validated(DTOObjectConstans.Create.class) @RequestBody OrderingUnitDTO simpleOrderingUnitDTO
			){
		var orderingUnit = orderingUnitService.createOrderingUnit(simpleOrderingUnitDTO);
		return ResponseEntity.created(ServletUriComponentsBuilder
		        .fromCurrentRequest()
		        .path("/{id}")
		        .build(orderingUnit.getId())
		        )
		.body(orderingUnit);
	}
	
	@PutMapping(
			value="/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderingUnitDTO> updateUrderingUnit(
			@PathVariable String id,
			@RequestBody OrderingUnitDTO simpleOrderingUnitDTO
			) throws EntityNotFoundException{
		var orderingUnit = orderingUnitService.updateOrderingUnit(id, simpleOrderingUnitDTO);
		return ResponseEntity
				.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(orderingUnit);
	}
	
	@GetMapping(
			value= "/{id}",
			produces= {MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<OrderingUnitDTO> getOrderingUnitById(@PathVariable String id) throws EntityNotFoundException{
		var orderingUnit = orderingUnitService.getOrderingUnitById(id);
		return ResponseEntity
				.ok(orderingUnit);
	}
	
	@GetMapping(
			value= "/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<OrderingUnitDTO>> getOrderingUnitsByExample(
			@RequestParam(required = false) String shortName,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String country,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String street,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "SURNAME") SortConstantsOrderingUnit sortField,
			@RequestParam(required = true, defaultValue = "ASC") Direction direction
			){
		var orderingUnits = orderingUnitService.getOrderingUnitByExample(
				shortName, 
				name, 
				country, 
				city, 
				street,
				pageNumber, 
				pageSize, 
				sortField, 
				direction
				);
		return ResponseEntity
				.ok(orderingUnits);
	}
	
	@GetMapping(
			value= "/{orderingUnitId}/patientOrders",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<ListPatientOrderDTO>> getOrderingUnitPatientOrders(
			@PathVariable String orderingUnitId,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "CREATION_DATE") SortConstants sortField,
			@RequestParam(required = true, defaultValue = "DESC") Direction direction
			) throws EntityNotFoundException{
		var orders = patientOrderService.findAllOrderingUnitAndPatientOrders(
				orderingUnitId,
				null,
				pageNumber,
				pageSize,
				sortField,
				direction
				);
		return ResponseEntity
				.ok(orders);
	}
	

	@GetMapping(
			value= "/{orderingUnitId}/phisicians",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<PhisicianDTO>> getOrderingUnitPhisicians(
			@PathVariable String orderingUnitId
			) throws EntityNotFoundException{
		var phisicians = orderingUnitService.getOrderingUnitPhisicians(orderingUnitId);
		return ResponseEntity
				.ok(phisicians);
	}
	
	@PutMapping(
			value= "/{orderingUnitId}/phisicians/{phisicianId}"
			)
	public ResponseEntity<Void> addOrderingUnitPhisician(@PathVariable String orderingUnitId, @PathVariable String phisicianId) throws EntityNotFoundException{
			orderingUnitService.addOrderingUnitPhisician(orderingUnitId, phisicianId);
		return ResponseEntity
				.ok()
				.location(ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.build();
	}
	
	@DeleteMapping(
			value = "/{orderingUnitId}/phisicians/{phisicianId}"
			)
	public ResponseEntity<Void> removeOrderingUnitPhisician(@PathVariable String orderingUnitId, @PathVariable String phisicianId) throws EntityNotFoundException{
		orderingUnitService.removeOrderingUnitPhisician(orderingUnitId, phisicianId);
		return ResponseEntity
				.noContent()
				.build();	
	}
	

}
