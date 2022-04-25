package base.Services.baza1;

import java.util.List;
import java.util.stream.Collectors;


import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.DTO.baza1.OrderingUnitDTO.OrderingUnitDTO;
import base.DTO.baza1.PhisicianDTO.PhisicianDTO;
import base.Model.baza1.OrderingUnit;
import base.Model.baza1.Phisician;
import base.Repository.Baza1Repository.OrderingUnitRepository;
import base.Repository.Baza1Repository.PhisicianRepository;
import base.Utils.Exceptions.EntityNotFoundException;


@Service
public class PhisicianService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PhisicianRepository phisicianRepository;
	
	@Autowired
	private OrderingUnitRepository orderingUnitRepository;
	
	public enum SortConstantsPhisician{
		NAME("name"),
		SURNAME("surname"),
		PERSONAL_ID("personalIdentificationNumber"),
		CREATION_DATE("cretionTimeStamp");
		
		private String value;
		
		SortConstantsPhisician(String value) {
			this.value=value;
		}
		public String getValue() {
			return this.value;
		}
	}
	
	private ExampleMatcher phisicianMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("surname", GenericPropertyMatchers.contains())
			.withMatcher("personalIdentificationNumber", GenericPropertyMatchers.exact())
			.withIgnorePaths(
					"Id"
					);
			
	
	public PhisicianDTO createPhisician(@NotNull PhisicianDTO phisicianDTO) {
		var savedObject = phisicianRepository.save(modelMapper.map(phisicianDTO, Phisician.class));
		return modelMapper.map(
				savedObject,
				PhisicianDTO.class
				);
	}
	
	public PhisicianDTO updatePhisician(@NotNull String id, @NotNull PhisicianDTO phisicianDTO) throws EntityNotFoundException {
		var phisician = phisicianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		modelMapper.map(phisicianDTO, phisician);
		return modelMapper.map(
				phisicianRepository.save(phisician),
				PhisicianDTO.class
				);
	}
	
	public Page<PhisicianDTO> getPhisicianByExample(
			String name,
			String surname,
			String personalIdentificationNumber,
			@NotNull Integer pageNumber,
			@NotNull Integer pageSize,
			@NotNull SortConstantsPhisician sortField,
			@NotNull Direction direction
			) {
		var phisician = new Phisician();
		phisician.setName(name);
		phisician.setSurname(surname);
		phisician.setPersonalIdentificationNumber(personalIdentificationNumber);
		var page = PageRequest.of(
				pageNumber,
				pageSize,
				Sort.by(direction,sortField.getValue())
				);
		return phisicianRepository
				.findAll(Example.of(phisician, phisicianMatcher), page)
				.map(obj -> modelMapper.map(obj, PhisicianDTO.class));
	}

	
	public PhisicianDTO getPhisicianById(@NotNull String id) throws EntityNotFoundException {
		var phisician = phisicianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		return modelMapper.map(phisician, PhisicianDTO.class);
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public void addPhisicianOrderingUnits(@NotNull String phisicianId, @NotNull String orderingUnitId) throws EntityNotFoundException{
		Phisician phisician = phisicianRepository
				.findById(phisicianId)
				.orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		orderingUnitRepository
		.findById(orderingUnitId)
		.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class))
		.addPhisician(phisician);
		phisicianRepository.save(phisician);
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public void deletePhisicianOrderingUnit(@NotNull String phisicianId, @NotNull String orderingUnitId) throws EntityNotFoundException {
		var phisician = phisicianRepository
				.findById(phisicianId)
				.orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		orderingUnitRepository
		.findById(orderingUnitId)
		.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class))
		.removePhisician(phisician);
	}
	
	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public List<OrderingUnitDTO> getOrderingUnits(@NotNull String id) throws EntityNotFoundException{
		return phisicianRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Phisician.class))
				.getOrderingUnits()
				.stream()
				.map(obj -> modelMapper.map(obj, OrderingUnitDTO.class))
				.collect(Collectors.toList());		
	}

}
