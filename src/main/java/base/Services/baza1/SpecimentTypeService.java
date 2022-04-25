package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import base.DTO.baza1.PatientSampleDTO.SpecimentTypeDTO;
import base.Model.baza1.SpecimentType;
import base.Repository.Baza1Repository.SpecimentTypeRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class SpecimentTypeService {

	public SpecimentTypeRepository specimentTypeRepository;
	public ModelMapper modelMapper;
	
	public SpecimentTypeService(SpecimentTypeRepository specimentTypeRepository, ModelMapper modelMapper) {
		this.specimentTypeRepository = specimentTypeRepository;
		this.modelMapper = modelMapper;
	}

	public SpecimentTypeDTO create(SpecimentTypeDTO specimentTypeDTO) {
		var speciment = modelMapper.map(specimentTypeDTO, SpecimentType.class);
		return modelMapper.map(specimentTypeRepository.save(speciment), SpecimentTypeDTO.class);
	}
	
	public SpecimentTypeDTO update(String specimentTypeId, SpecimentTypeDTO specimentTypeDTO) {
		var speciment = specimentTypeRepository.findById(specimentTypeId).orElseThrow(() -> new EntityNotFoundException(SpecimentType.class));
		modelMapper.map(specimentTypeDTO, speciment);
		return modelMapper.map(specimentTypeRepository.save(speciment), SpecimentTypeDTO.class);
	}
	
	public void setActiveStatus(String specimentTypeId, Boolean isActive) {
		specimentTypeRepository.activeStatus(specimentTypeId, isActive);
	}
	
	public SpecimentTypeDTO getById(String specimentTypeId) {
		var speciment = specimentTypeRepository.findById(specimentTypeId).orElseThrow(() -> new EntityNotFoundException(SpecimentType.class));
		return modelMapper.map(speciment, SpecimentTypeDTO.class);
	}
	
	public List<SpecimentTypeDTO> getAll() {
		return specimentTypeRepository
				.findAll()
				.stream()
				.map(obj -> modelMapper.map(obj, SpecimentTypeDTO.class))
				.collect(Collectors.toList());
	}
	
	

}
