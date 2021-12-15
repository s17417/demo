package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.Method;
import base.Repository.Baza1Repository.LaboratoryTestRepository;
import base.Repository.Baza1Repository.MethodRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class LaboratoryTestService {
	
	@Autowired
	private LaboratoryTestRepository laboratoryTestRepository;
	
	@Autowired
	private MethodRepository methodRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public LaboratoryTestDTO create(@NotNull LaboratoryTestDTO laboratoryTestDTO) {
		var laboratoryTest = laboratoryTestRepository
				.save(modelMapper.map(laboratoryTestDTO, LaboratoryTest.class));
		return modelMapper
				.map(laboratoryTest, LaboratoryTestDTO.class);
		
	}
	
	public LaboratoryTestDTO update(@NotNull String id, @NotNull LaboratoryTestDTO laboratoryTestDTO) {
		var laboratoryTest = laboratoryTestRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		modelMapper.map(laboratoryTestDTO, laboratoryTest);
		return modelMapper
				.map(laboratoryTestRepository.save(laboratoryTest),LaboratoryTestDTO.class);
		
	}
	
	public LaboratoryTestDTO getById(@NotNull String id) {
		
		var laboratoryTest = laboratoryTestRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		
		return modelMapper
				.map(laboratoryTest,LaboratoryTestDTO.class);
		
	}
	
	public List<LaboratoryTestDTO> getAll(){
		return laboratoryTestRepository
				.findAll()
				.stream()
				.map(obj -> modelMapper.map(obj, LaboratoryTestDTO.class))
				.collect(Collectors.toList());	
	}
	
	@Transactional(value = "laboratoryTransactionManager",readOnly = true)
	public List<? extends AbstractMethodDTO> getMethods(@NotNull String id){
		var laboratoryTest = laboratoryTestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		return laboratoryTest
				.getMethods()
				.stream()
				.map( obj -> modelMapper.map(obj, AbstractMethodDTO.class))
				.collect(Collectors.toList());
	}


}
