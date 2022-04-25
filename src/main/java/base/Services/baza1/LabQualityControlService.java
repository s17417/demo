package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.Model.baza1.LabQualityControl;
import base.Repository.Baza1Repository.LabQualityControlRepository;
import base.Services.baza1.PatientOrderService.SortConstants;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class LabQualityControlService {
	
	private LabQualityControlRepository labQualityControlRepository;
	
	private ModelMapper modelMapper;
	
	public enum SortConstants{
		NAME("name"),
		EXTERNAL_IDENTIFICATION("externalIdentificationCode"),
		ORDER_IDENTIFICATION("orderIdentificationCode"),
		CREATION_DATE("cretionTimeStamp");
		
		private String value;
		
		SortConstants(String value) {
			this.value=value;
		}
		public String getValue() {
			return this.value;
		}
	}
	
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
			.withMatcher("orderIdentificationCode", GenericPropertyMatchers.exact())
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
	
	public Page<LabQualityControlDTO> findByExample(
			String name, 
			String externalIdentificationCode,
			String orderIdentification,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			){
		var labQualityControl = new LabQualityControl();
		labQualityControl.setName(name);
		labQualityControl.setExternalIdentificationCode(externalIdentificationCode);
		labQualityControl.setOrderIdentificationCode(orderIdentification);
		var example = Example.of(labQualityControl, labQualityControlMatcher);
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				 Sort.by(direction,sortField.getValue())
				);
		
		return labQualityControlRepository.findAll(example, page)
				.map(obj -> modelMapper.map(obj, LabQualityControlDTO.class));
	}
	
	public void delete(@NotNull String id) {
		labQualityControlRepository.deleteById(id);
	}

}
