package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import base.DTO.baza1.AnalyteDTO.AnalyteDTO;
import base.Model.baza1.Analyte;
import base.Repository.Baza1Repository.AnalyteRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class AnalyteService {
	
	@Autowired
	private AnalyteRepository analyteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
	private final ExampleMatcher analyteMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("shortName", GenericPropertyMatchers.contains())
			.withIgnorePaths("Id","description");

	
	public AnalyteDTO createAnalyte(@NotNull AnalyteDTO analyteDTO) {
		var analyte = analyteRepository
				.save(modelMapper.map(analyteDTO, Analyte.class));				
		return modelMapper
				.map(analyte, AnalyteDTO.class);
	}
	
	public AnalyteDTO updateAnalyte(@NotNull String id, @NotNull AnalyteDTO analyteDTO) {
		var analyte = analyteRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Analyte.class));
		modelMapper.map(analyteDTO, analyte);
		return modelMapper
				.map(analyteRepository.save(analyte), AnalyteDTO.class);
	}
	
	public AnalyteDTO getById(@NotNull String id) {
		var analyte = analyteRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Analyte.class));
		return modelMapper
				.map(analyte, AnalyteDTO.class);
	}
	
	public List<AnalyteDTO> getAll(){
		return analyteRepository
				.findAll()
				.stream()
				.map(obj -> modelMapper.map(obj, AnalyteDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<AnalyteDTO> getByExample(
			String shortName,
			String name,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstants sortField,
			@NotNull Direction direction
			){
		var analyte = new Analyte();
		analyte.setName(name);
		analyte.setShortName(shortName);
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				Sort.by(direction,sortField.getValue())
				);
		return analyteRepository
				.findAll(Example.of(analyte, analyteMatcher),page)
				.map(obj -> modelMapper.map(obj, AnalyteDTO.class));
	}
	
	public void deleteAnalyte (@NotNull String id) {
		analyteRepository.deleteById(id);
	}


}
