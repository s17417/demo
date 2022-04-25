package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.SpecimentType;
import base.Repository.Baza1Repository.LaboratoryTestRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class LaboratoryTestService {
	

	private LaboratoryTestRepository laboratoryTestRepository;
	
	private ModelMapper modelMapper;		
	
	public LaboratoryTestService(LaboratoryTestRepository laboratoryTestRepository, ModelMapper modelMapper) {
		this.laboratoryTestRepository = laboratoryTestRepository;
		this.modelMapper = modelMapper;
	}

	public enum SortConstants{
		NAME("name"),
		SHORTNAME("shortName"),
		CREATION_DATE("cretionTimeStamp");
		
		private String value;
		
		SortConstants(String value) {
			this.value=value;
		}
		public String getValue() {
			return this.value;
		}
	}
	
	private ExampleMatcher laboratoryTestMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("shortName", GenericPropertyMatchers.contains())
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("specimentType.id", GenericPropertyMatchers.exact())

			.withIgnorePaths(
					"Id",
					"isActive",
					"specimentType.speciment",
					"specimentType.isActive",
					"labTestOrder",
					"methods"
					);
	
	@Transactional(value = "laboratoryTransactionManager")
	public LaboratoryTestDTO create(@NotNull LaboratoryTestDTO laboratoryTestDTO) {
		var laboratoryTest = laboratoryTestRepository
				.saveAndFlush(modelMapper.map(laboratoryTestDTO, LaboratoryTest.class));
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
	
	public Page<LaboratoryTestDTO> getAllByExample(
			String shortName,
			String name,
			String specimentTypeId,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			){
		var laboratoryTest = new LaboratoryTest();
		laboratoryTest.setShortName(shortName);
		laboratoryTest.setName(name);
		var specimentType=new SpecimentType();
		specimentType.setId(specimentTypeId);
		laboratoryTest.setSpecimentType(specimentType);
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				Sort.by(direction,sortField.getValue())
				);
		return laboratoryTestRepository
				.findAll(Example.of(laboratoryTest,laboratoryTestMatcher), page)
				.map(obj -> modelMapper.map(obj, LaboratoryTestDTO.class));
	}
	
	@Transactional(value = "laboratoryTransactionManager",readOnly = true)
	public List<? extends AbstractMethodDTO> getMethods(
			@NotNull String id,
			Boolean onlyActive){
		var laboratoryTest = laboratoryTestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(LaboratoryTest.class));
		return laboratoryTest
				.getMethods()
				.stream()
				.filter(obj -> onlyActive?obj.getIsActive():true)
				.map( obj -> modelMapper.map(obj, AbstractMethodDTO.class))
				.collect(Collectors.toList());
	}


}
