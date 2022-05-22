package base.Services.baza1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.labTestOrderDTO.LabQualityControlResultDTO;
import base.DTO.baza1.labTestOrderDTO.OrderResultDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleLabQualityControlResultDTO;
import base.DTO.baza1.labTestOrderDTO.SimpleOrderResultDTO;
import base.Model.baza1.ControlSample;
import base.Model.baza1.LabQualityControlResult;
import base.Model.baza1.OrderResult;
import base.Model.baza1.OrderStatus;
import base.Model.baza1.PatientSample;
import base.Repository.Baza1Repository.ControlSampleRepository;
import base.Repository.Baza1Repository.LabQualityControlRepository;
import base.Repository.Baza1Repository.LabQualityControlResultRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class LabQualityControlResultService {

	private LabQualityControlResultRepository labQualityControlResultRepository;
	
	private ControlSampleRepository controlSampleRepository;
	
	private ModelMapper modelMapper;

	public LabQualityControlResultService(LabQualityControlResultRepository labQualityControlResultRepository,
			ControlSampleRepository controlSampleRepository, ModelMapper modelMapper) {
		this.labQualityControlResultRepository = labQualityControlResultRepository;
		this.controlSampleRepository = controlSampleRepository;
		this.modelMapper = modelMapper;
	}
	


	/*
	 * CreateOrder
	 */
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultDTO create(
			@NotNull String controlOrderId,
			@NotNull String controlSampleId, 
			@NotNull SimpleLabQualityControlResultDTO labQualityControlResultDTO
		)throws EntityNotFoundException {
		
		var controlSample = controlSampleRepository
				.findByOrderIdAndSampleId(controlOrderId, controlSampleId)
				.orElseThrow(() -> new EntityNotFoundException(ControlSample.class));
		
		var labQualityControlResult = modelMapper.map(labQualityControlResultDTO, LabQualityControlResult.class, DTOObjectConstans.CREATE.name());
		labQualityControlResult.setSample(controlSample);
		labQualityControlResult.createAnalyteResults();
		
		/*System.out.println(
				labQualityControlResultRepository
				.findTargetValue(controlOrderId, controlSampleId, labQualityControlResultDTO.getLaboratoryTest().getId()).get()
				);*/
		
		var labQualityControlTargetValue = labQualityControlResultRepository
				.findTargetValue(controlOrderId, controlSampleId, labQualityControlResultDTO.getLaboratoryTest().getId())
				.orElseGet(() -> LabQualityControlResult.createTargetLabQualityControlResult(labQualityControlResult.getLaboratoryTest(), controlSample));
		labQualityControlResult.setParentTargetValue(labQualityControlTargetValue);
		
		var obj=labQualityControlResultRepository.saveAndFlush(labQualityControlResult);
		return modelMapper
				.map(obj, LabQualityControlResultDTO.class);
	}
	
	
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultDTO update(@NotNull String controlOrderId,  @NotNull String controlSampleId, @NotNull String controlResultId, @NotNull SimpleLabQualityControlResultDTO controlResultDTO)throws EntityNotFoundException{
		//TO DO
		var controlResult = labQualityControlResultRepository
				.findByIdAndOrder(controlOrderId, controlSampleId, controlResultId)
				.orElseThrow(() -> new EntityNotFoundException(LabQualityControlResult.class));		
		
		modelMapper.map(controlResultDTO, controlResult, DTOObjectConstans.UPDATE.name());
		if(controlResult.getState().equals(OrderStatus.PENDING)||controlResult.getState().equals(OrderStatus.CANCELLED))
			controlResult.setState(OrderStatus.PENDING);
		else controlResult.setState(OrderStatus.PROCESSED);
		labQualityControlResultRepository.save(controlResult);
		
		return modelMapper
				.map(labQualityControlResultRepository.saveAndFlush(controlResult), LabQualityControlResultDTO.class);	
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultDTO getById(
			@NotNull String controlOrderId,
			@NotNull String controlSampleId,
			@NotNull String controlResultId
			)throws EntityNotFoundException{
		return labQualityControlResultRepository
				.findByIdAndOrder(controlOrderId, controlSampleId, controlResultId)
				.map(obj -> modelMapper.map(obj, LabQualityControlResultDTO.class))
				.orElseThrow(() -> new EntityNotFoundException(LabQualityControlResult.class));				
	}
	

	/**
	 *  Get Control result for orderId
	 * @param controlOrderId
	 * @return
	 */
	public List<LabQualityControlResultDTO> getControlOrderResults(@NotNull String controlOrderId) {
		return labQualityControlResultRepository.findAllByOrderId(controlOrderId)
				.stream()
				.map(obj -> modelMapper.map(obj, LabQualityControlResultDTO.class))
				.collect(Collectors.toList());
	}
	
	
	/*
	 * Cancel Order
	 */
	@Transactional(value = "laboratoryTransactionManager")
	public LabQualityControlResultDTO cancelOrder(@NotNull String controlOrderId,  @NotNull String controlSampleId, @NotNull String labQualityControlResultId) {
		var controlResult = labQualityControlResultRepository
				.findByIdAndOrder(controlOrderId, controlSampleId, labQualityControlResultId)
				.orElseThrow(() -> new EntityNotFoundException(LabQualityControlResult.class));
		if (controlResult.getTargetValue()!=true) controlResult.setState(OrderStatus.CANCELLED);
		return modelMapper
				.map(labQualityControlResultRepository.saveAndFlush(controlResult), LabQualityControlResultDTO.class);
	}

}
