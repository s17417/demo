package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.Model.baza1.LabQualityControl;
import base.Repository.Baza1Repository.LabQualityControlRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class LabQualityControlService {
	
	private LabQualityControlRepository labQualityControlRepository;
	
	private ModelMapper modelMapper;
	
	public LabQualityControlService(LabQualityControlRepository labQualityControlRepository, ModelMapper modelMapper) {
		this.labQualityControlRepository = labQualityControlRepository;
		this.modelMapper = modelMapper;
	}

	private final ExampleMatcher labQualityControlMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("externalIdentificationCode", GenericPropertyMatchers.contains())
			.withMatcher("orderIdentification", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id");

	public LabQualityControlDTO create(@NotNull LabQualityControlDTO labQualityControlDTO) {
		var labQualityControl = labQualityControlRepository.save(
				modelMapper.map(labQualityControlDTO, LabQualityControl.class)
				);
		return modelMapper.map(labQualityControl, LabQualityControlDTO.class);
	}
	
	public LabQualityControlDTO update(@NotNull String id, @NotNull LabQualityControlDTO labQualityControlDTO) {
		var labQualityControl = labQualityControlRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(LabQualityControl.class));
		modelMapper.map(labQualityControlDTO, labQualityControl);
		
		return modelMapper.map(
				labQualityControlRepository.saveAndFlush(labQualityControl),
				LabQualityControlDTO.class
				);
	}
	
	public LabQualityControlDTO getById(@NotNull String id) {
		var labQualityControl = labQualityControlRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(LabQualityControl.class));
		return modelMapper.map(labQualityControl, LabQualityControlDTO.class);
	}
	
	public List<LabQualityControlDTO> findByExample(
			String name, 
			String externalIdentificationCode,
			String orderIdentification
			){
		var labQualityControl = new LabQualityControl();
		labQualityControl.setName(name);
		labQualityControl.setExternalIdentificationCode(externalIdentificationCode);
		labQualityControl.setOrderIdentificationCode(orderIdentification);
		var example = Example.of(labQualityControl, labQualityControlMatcher);
		
		return labQualityControlRepository.findAll(example)
				.stream()
				.map(obj -> modelMapper.map(obj, LabQualityControlDTO.class))
				.collect(Collectors.toList());
	}
	
	public void delete(@NotNull String id) {
		labQualityControlRepository.deleteById(id);
	}

}
