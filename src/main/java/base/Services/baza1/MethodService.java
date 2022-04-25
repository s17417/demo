package base.Services.baza1;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.Method;
import base.Repository.Baza1Repository.LaboratoryTestRepository;
import base.Repository.Baza1Repository.MethodRepository;
import base.Utils.Exceptions.EntityNotFoundException;
import base.Utils.Exceptions.RelationNotFoundException;

@Service
public class MethodService {

	private MethodRepository methodRepository;
	
	private LaboratoryTestRepository laboratoryTestRepository;
	
	private ModelMapper modelMapper;
	
	private final ExampleMatcher methodMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("analyte.Id", GenericPropertyMatchers.exact())
			.withMatcher("laboratoryTest.Id", GenericPropertyMatchers.exact())
			.withIgnorePaths("Id");
	
	public MethodService(MethodRepository methodRepository, ModelMapper modelMapper, LaboratoryTestRepository laboratoryTestRepository) {
		this.methodRepository = methodRepository;
		this.modelMapper = modelMapper;
		this.laboratoryTestRepository=laboratoryTestRepository;
	}
	
	@Transactional("laboratoryTransactionManager")
	public <T extends AbstractMethodDTO> T create(@NotNull String id, @NotNull T abstractMethodDTO) throws EntityNotFoundException {
		var laboratoryTest= laboratoryTestRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		var method = modelMapper.map(abstractMethodDTO, Method.class);
		method.setLaboratoryTest(laboratoryTest);
		modelMapper.map(methodRepository.saveAndFlush(method), abstractMethodDTO);
		return abstractMethodDTO;
	}
	
	@Transactional("laboratoryTransactionManager")
	public <T extends AbstractMethodDTO> T update(@NotNull String laboratoryTestId, @NotNull String methodId, @NotNull T abstractMethodDTO)  throws EntityNotFoundException, RelationNotFoundException {
		var laboratoryTest = laboratoryTestRepository
				.findById(laboratoryTestId)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));

		var method = laboratoryTest
				.getMethods()
				.stream()
				.filter( obj -> obj.getId().contentEquals(methodId))
				.findFirst().orElseThrow(() ->  new RelationNotFoundException(LaboratoryTest.class, Method.class));
			
		modelMapper.map(abstractMethodDTO, method);
		method = methodRepository.saveAndFlush(method);
		modelMapper.map(method, abstractMethodDTO);
		return abstractMethodDTO;	
	}
	
	@Transactional(value = "laboratoryTransactionManager",readOnly = true)
	public AbstractMethodDTO getById(@NotNull String laboratoryTestId, @NotNull String methodId) {
		var laboratoryTest = laboratoryTestRepository
				.findById(laboratoryTestId)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		var method = laboratoryTest
		.getMethods()
		.stream()
		.filter( obj -> obj.getId().contentEquals(methodId))
		.findFirst().orElseThrow(() ->  new RelationNotFoundException(LaboratoryTest.class, Method.class));

		return  modelMapper.map(method, AbstractMethodDTO.class);
	}
	
	//@Transactional(value = "laboratoryTransactionManager")
	public void deleteById(@NotNull String laboratoryTestId, @NotNull String methodId){
		/*var laboratoryTest = laboratoryTestRepository
				.findById(laboratoryTestId)
				.orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		if(laboratoryTest.getMethods().stream().anyMatch(obj -> obj.getId().contentEquals(methodId)))
				//.removeIf(obj -> obj.getId()
				//.contentEquals(methodId));*/
			methodRepository.deleteById(methodId);
		
	}

}
