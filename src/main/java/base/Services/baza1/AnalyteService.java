package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import base.DTO.baza1.AnalyteDTO;
import base.Model.baza1.Analyte;
import base.Repository.Baza1Repository.AnalyteRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class AnalyteService {
	
	@Autowired
	private AnalyteRepository analyteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
	public List<AnalyteDTO> getByExample(String shortName, String name){
		var analyte = new Analyte();
		analyte.setName(name);
		analyte.setShortName(shortName);
		return analyteRepository
				.findAll(Example.of(analyte, analyteMatcher))
				.stream()
				.map(obj -> modelMapper.map(obj, AnalyteDTO.class))
				.collect(Collectors.toList());
	}
	
	public void deleteAnalyte (@NotNull String id) {
		analyteRepository.deleteById(id);
	}


}
