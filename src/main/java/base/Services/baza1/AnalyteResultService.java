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
import base.DTO.baza1.labTestOrderDTO.OrderResultWithResultListDTO;
import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.OrderResult;
import base.Model.baza1.PatientOrder;
import base.Repository.Baza1Repository.AnalyteResultRepository;
import base.Repository.Baza1Repository.OrderResultRepository;
import base.Utils.Exceptions.EntityNotFoundException;
import base.Utils.Exceptions.RelationNotFoundException;

@Service
public class AnalyteResultService {
	
	private OrderResultRepository orderResultRepository;
	private AnalyteResultRepository analyteResultRepository;	
	private ModelMapper modelMapper;

	public AnalyteResultService(OrderResultRepository orderResultRepository,AnalyteResultRepository analyteResultRepository, ModelMapper modelMapper) {
		this.orderResultRepository=orderResultRepository;
		this.analyteResultRepository = analyteResultRepository;
		this.modelMapper = modelMapper;
	}
	/*UTWORZYC METODE W KLASIE BASTRAKCJYJNEJ ZWRACAJACĄ NOWĄ INSTANCJE ANALATYRESULT!!!!*/

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
	public OrderResultWithResultListDTO getAllWithOrder(
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
		
		modelMapper.map(orderResult, order);
		var result=orderResultRepository.save(order);
		return modelMapper.map(result, OrderResultWithResultListDTO.class);		
	}
}
