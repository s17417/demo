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
	
	public List<PhisicianDTO> getPhisicianByExample(
			String name,
			String surname,
			String personalIdentificationNumber
			) {
		var phisician = new Phisician();
		phisician.setName(name);
		phisician.setSurname(surname);
		phisician.setPersonalIdentificationNumber(personalIdentificationNumber);
		return phisicianRepository
				.findAll(Example.of(phisician, phisicianMatcher))
				.stream()
				.map(obj -> modelMapper.map(obj, PhisicianDTO.class))
				.collect(Collectors.toList());
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
