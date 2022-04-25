package base.Controllers.baza1;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.MethodDTO.ListMethodDTO;
import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.DTO.baza1.PatientSampleDTO.PatientSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;
import base.DTO.baza1.labTestOrderDTO.ListOrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultWithResultListDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleOrderResultDTO;
import base.Model.baza1.OrderStatus;
import base.Model.baza1.TatMode;
import base.Services.baza1.AnalyteResultService;
import base.Services.baza1.OrderResultService;
import base.Services.baza1.PatientOrderService;
import base.Services.baza1.PatientOrderService.SortConstants;
import base.Utils.Exceptions.EntityNotFoundException;

@Validated
@RestController
@RequestMapping(value="/lab/patientOrders")
@CrossOrigin
public class PatientOrderController {

	private PatientOrderService patientOrderService;
	private OrderResultService orderResultService;
	private AnalyteResultService analyteResultService;
	
	public PatientOrderController(
			PatientOrderService patientOrderService,
			OrderResultService orderResultService,
			AnalyteResultService analyteResultService
			) {
		this.patientOrderService = patientOrderService;
		this.orderResultService = orderResultService;
		this.analyteResultService= analyteResultService;
	}

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
			value= "/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<PatientOrderDTO> updatePatientOrder(
			@NotNull @PathVariable String id,
			@Validated(DTOObjectConstans.Create.class) @RequestBody PatientOrderDTO patientOrderDTO
			) throws EntityNotFoundException {
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
	public ResponseEntity<Page<ListPatientOrderDTO>> getAllPatientOrders(
			@RequestParam(required = false) @Size(min=3, max=60) String name,
			@RequestParam(required = false) @Size(min=3, max=120) String surname,
			@RequestParam(required = false) String personalIdentificationNumber,
			@RequestParam(required = false) String orderIdentificationCode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromOrderDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toOrderDate,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "25") Integer pageSize,
			@RequestParam(required = true, defaultValue = "CREATION_DATE") SortConstants sortField,
			@RequestParam(required = true, defaultValue = "DESC") Direction direction
			){
		var patientOrder = patientOrderService.findAllPatientOrders(
				name,
				surname,
				personalIdentificationNumber,
				orderIdentificationCode,
				fromBirthDate,
				toBirthDate,
				fromOrderDate,
				toOrderDate,
				pageNumber,
				pageSize,
				sortField,
				direction);
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
	
	@PostMapping(
			value= "/{patientOrderId}/patientSamples/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimplePatientSampleDTO> createPatientSample(
			@PathVariable String patientOrderId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimplePatientSampleDTO patientSampleDTO
			) throws EntityNotFoundException {
		var patientSample = patientOrderService.createSample(patientOrderId, patientSampleDTO);
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{patientOrderId}/patientSamples/{id}")
						.build(patientOrderId, patientSample.getId()))
				.body(patientSample);
	}
	
	@PutMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SimplePatientSampleDTO> updatePatientSample(
			@PathVariable String patientOrderId,
			@PathVariable String patientSampleId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimplePatientSampleDTO patientSampleDTO
			) throws EntityNotFoundException {
		var patientSample = patientOrderService.updateSample(patientOrderId, patientSampleId, patientSampleDTO);
		return ResponseEntity
				.ok(patientSample);
	}
	
	@GetMapping(
			value= "/{patientOrderId}/patientSamples/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SimplePatientSampleDTO>> getPatientOrderSamples(@PathVariable String patientOrderId){
		var patientSamples = patientOrderService.getSamples(patientOrderId);
		return ResponseEntity
				.ok(patientSamples);		
	}	
	
	
	
	@PostMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultDTO> createOrderResult(
			@PathVariable String patientOrderId,
			@PathVariable String patientSampleId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody SimpleOrderResultDTO orderResultDTO
			) throws EntityNotFoundException {
		var orderResult = orderResultService.create(patientOrderId,patientSampleId,orderResultDTO);
		
		return ResponseEntity
				.created(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.build(orderResult.getId()))
				.body(orderResult);
	}
	
	
	@PutMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/{orderResultId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultDTO> updateOrderResult(
			@PathVariable String orderResultId,
			@PathVariable String patientSampleId,			
			@PathVariable String patientOrderId,
			@Validated @RequestBody SimpleOrderResultDTO orderResultDTO
			) throws EntityNotFoundException {
		var orderResult = orderResultService.update(patientOrderId, patientSampleId, orderResultId, orderResultDTO);	
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(orderResult);
	}
	
	@PutMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/{orderResultId}/cancel",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultDTO> cancelOrderResult(
			@PathVariable String orderResultId,
			@PathVariable String patientSampleId,			
			@PathVariable String patientOrderId
			) throws EntityNotFoundException {
		var orderResult = orderResultService.cancelOrder(patientOrderId, patientSampleId, orderResultId);	
		return ResponseEntity.ok()
				.location(
						ServletUriComponentsBuilder
						.fromCurrentRequest()
						.build()
						.toUri())
				.body(orderResult);
	}
	
	@GetMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/{orderResultId}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultDTO> getOrderResults(
			@PathVariable String patientOrderId,
			@PathVariable String patientSampleId,
			@PathVariable String orderResultId
			) throws EntityNotFoundException {
		var orderResult = orderResultService.getById(patientOrderId,patientSampleId,orderResultId);
		return ResponseEntity
				.ok(orderResult);
	}
	
	@GetMapping(
			value= "/{patientOrderId}/patientSamples/ordersResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<OrderResultDTO>> getOrderResult(
			@PathVariable String patientOrderId
			)throws EntityNotFoundException {
		var orderResults = orderResultService.getPatientOrderResults(patientOrderId);
		return ResponseEntity
				.ok(orderResults);
	}
	
	@GetMapping(
			value= "/patientSamples/orderResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Page<ListOrderResultDTO>> getAllOrderResults(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,
			@RequestParam(required = false) String personalIdentificationNumber,
			@RequestParam(required = false) String orderIdentification,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toBirthDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate fromOrderDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent LocalDate toOrderDate,
			@RequestParam(required = false) TatMode tatMode,
			@RequestParam(required = false) OrderStatus labTestOrderStatus,
			@RequestParam(required = false) String methodId,
			@RequestParam(required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = true, defaultValue = "50") Integer pageSize,
			@RequestParam(required = true, defaultValue = "ORDER_DATE") base.Services.baza1.OrderResultService.SortConstants sortField,
			@RequestParam(required = true, defaultValue = "DESC") Direction direction
			){
		var orderResult = orderResultService.getAllOrderResults(
				name,
				surname,
				personalIdentificationNumber,
				orderIdentification,
				fromBirthDate,
				toBirthDate,
				fromOrderDate,
				toOrderDate,
				tatMode,
				labTestOrderStatus,
				methodId,
				pageNumber,
				pageSize,
				sortField,
				direction
				);
		return ResponseEntity
				.ok(orderResult);
	}
	
	@GetMapping(
			value= "/{patientOrderId}/ordersResults/{orderResultId}/analyteResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<? extends AbstractAnalyteResultDTO<?,? extends ListMethodDTO>>> getAnalyteResults(
			@PathVariable String patientOrderId,
			@PathVariable String orderResultId
			)throws EntityNotFoundException {
		List<? extends AbstractAnalyteResultDTO<?,? extends ListMethodDTO>> analyteResults=null;
		try {
		analyteResults = analyteResultService.getAll(patientOrderId, orderResultId);
		} catch(Exception e) {
			e.printStackTrace();
		};
		return ResponseEntity
				.ok(analyteResults);
	}
	
	@GetMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/{orderResultId}/analyteResults/",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultWithResultListDTO> getAnalyteResultsWithOrder(
			@PathVariable String patientOrderId,
			@PathVariable String patientSampleId,
			@PathVariable String orderResultId
			)throws EntityNotFoundException {
		System.out.println("dlckmdslcnslkdncksdjncksjcnksn");
		var analyteResults = analyteResultService.getAllWithOrder(patientOrderId, patientSampleId, orderResultId);
		System.out.println(analyteResults.getAnalyteResults());
		return ResponseEntity
				.ok(analyteResults);
	}
	
	@PutMapping(
			value= "/{patientOrderId}/patientSamples/{patientSampleId}/ordersResults/{orderResultId}/analyteResults/",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<OrderResultWithResultListDTO> updateResult(
			@PathVariable String patientOrderId,
			@PathVariable String patientSampleId,
			@PathVariable String orderResultId,
			@Validated(DTOObjectConstans.Create.class) @RequestBody OrderResultWithResultListDTO orderResultDTO
			) throws EntityNotFoundException {
		
		var result = analyteResultService.updateResult(patientOrderId, patientSampleId, orderResultId, orderResultDTO);
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
