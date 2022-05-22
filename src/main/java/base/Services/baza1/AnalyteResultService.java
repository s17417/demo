package base.Services.baza1;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.envers.internal.entities.mapper.id.IdMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.DTO.baza1.MethodDTO.ListMethodDTO;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultWithListDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultWithResultListDTO;
import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.LabQualityControlResult;
import base.Model.baza1.OrderResult;
import base.Model.baza1.OrderStatus;
import base.Model.baza1.PatientOrder;
import base.Repository.Baza1Repository.AnalyteResultRepository;
import base.Repository.Baza1Repository.LabQualityControlResultRepository;
import base.Repository.Baza1Repository.OrderResultRepository;
import base.Utils.Exceptions.EntityNotFoundException;
import base.Utils.Exceptions.RelationNotFoundException;

@Service
public class AnalyteResultService {
	
	private LabQualityControlResultRepository labQualityControlResultRepository;
	private OrderResultRepository orderResultRepository;
	private AnalyteResultRepository analyteResultRepository;	
	private ModelMapper modelMapper;

	
	/*UTWORZYC METODE W KLASIE BASTRAKCJYJNEJ ZWRACAJACĄ NOWĄ INSTANCJE ANALATYRESULT!!!!*/

	public AnalyteResultService(LabQualityControlResultRepository labQualityControlResultRepository,
			OrderResultRepository orderResultRepository, AnalyteResultRepository analyteResultRepository,
			ModelMapper modelMapper) {
		this.labQualityControlResultRepository = labQualityControlResultRepository;
		this.orderResultRepository = orderResultRepository;
		this.analyteResultRepository = analyteResultRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional(value = "laboratoryTransactionManager")
	public List<? extends AbstractAnalyteResultDTO<?, ? extends ListMethodDTO>> getAll(
			@NotNull String patientorderId,
			@NotNull String orderResultId
			) throws RelationNotFoundException {
		var resultList =  analyteResultRepository
				.findAnalyteResultsByPatientOrderIdAndOrderResultId(patientorderId, orderResultId);
		return resultList
				.stream()
				.map(obj -> modelMapper.map(obj, AbstractAnalyteResultDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultWithResultListDTO getAllWithPatientOrder(
			@NotNull String patientorderId,
			@NotNull String patientSampleId,
			@NotNull String orderResultId
			) throws RelationNotFoundException {
		
		var order = analyteResultRepository.findAnalyteResultsByPatientOrderIdAndOrderResultIdWithPatientOrder(patientorderId, orderResultId)
				.orElseThrow(()-> new EntityNotFoundException(OrderResult.class));
		
		/*var order = orderResultRepository.findByIdAndOrder(patientorderId, patientSampleId, orderResultId);
		List<AbstractAnalyteResultDTO<?,?>> resultList =  analyteResultRepository
				.findAnalyteResultsByPatientOrderIdAndOrderResultId(patientorderId, orderResultId)
				.stream()
				.map(obj -> modelMapper.map(obj, AbstractAnalyteResultDTO.class))
				.collect(Collectors.toList());*/
		var result =  modelMapper.map(order, OrderResultWithResultListDTO.class);
		return result;
		
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultWithListDTO getAllWithControlOrder(
			@NotNull String controlOrderId,
			@NotNull String controlSampleId,
			@NotNull String controlResultId
			) throws RelationNotFoundException {
		
		var order = analyteResultRepository.findAnalyteResultsByControlOrderIdAndControlResultId(controlOrderId, controlResultId)
				.orElseThrow(()-> new EntityNotFoundException(OrderResult.class));
				
		return modelMapper.map(order, LabQualityControlResultWithListDTO.class);		
	}
	
	
	@Transactional(value = "laboratoryTransactionManager")
	public OrderResultWithResultListDTO updateResult(
			@NotNull String patientorderId,
			@NotNull String patientSampleId,
			@NotNull String orderResultId,
			@NotNull OrderResultWithResultListDTO orderResult
			) throws RelationNotFoundException {
		
		var order = analyteResultRepository
				.findAnalyteResultsByPatientOrderIdAndOrderResultIdWithPatientOrder(patientorderId, orderResultId)
				.orElseThrow(()-> new EntityNotFoundException(OrderResult.class));
		
		Map<String,? extends AbstractAnalyteResult<?,?>> idMap = order
				.getAnalyteResults()
				.stream()
				.collect(Collectors.toMap(AbstractAnalyteResult::getId,Function.identity()));
		
		
		orderResult
				.getAnalyteResults()
				.stream()
				.filter(obj -> obj.getId()!=null) 
				.filter(obj -> idMap.containsKey(obj.getId()))
				.forEach(obj -> modelMapper.map(obj, idMap.get(obj.getId())));
		
		if (order.getAnalyteResults().stream().anyMatch(obj -> obj.getResult()!=null))
			order.setState(OrderStatus.PROCESSED);
		
		
		modelMapper.map(orderResult, order);
		var result=orderResultRepository.save(order);
		return modelMapper.map(result, OrderResultWithResultListDTO.class);		
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultWithListDTO updateControlResult(
			@NotNull String controlOrderId,
			@NotNull String controlSampleId,
			@NotNull String controlResultId,
			@NotNull LabQualityControlResultWithListDTO controlResult
			) throws RelationNotFoundException {
		
		var order = analyteResultRepository
				.findAnalyteResultsByControlOrderIdAndControlResultId(controlOrderId, controlResultId)
				.orElseThrow(()-> new EntityNotFoundException(LabQualityControlResult.class));
		
		Map<String,? extends AbstractAnalyteResult<?,?>> idMap = order
				.getAnalyteResults()
				.stream()
				.collect(Collectors.toMap(AbstractAnalyteResult::getId,Function.identity()));
		
		
		controlResult
				.getAnalyteResults()
				.stream()
				.filter(obj -> obj.getId()!=null) 
				.filter(obj -> idMap.containsKey(obj.getId()))
				.forEach(obj -> modelMapper.map(obj, idMap.get(obj.getId())));
		
		modelMapper.map(controlResult, order);
		var result=labQualityControlResultRepository.save(order);
		return modelMapper.map(result, LabQualityControlResultWithListDTO.class);		
	}
}
