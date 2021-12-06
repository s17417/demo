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
import base.Model.baza1.Address;
import base.Model.baza1.OrderingUnit;
import base.Model.baza1.Phisician;
import base.Repository.Baza1Repository.OrderingUnitRepository;
import base.Repository.Baza1Repository.PhisicianRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class OrderingUnitService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderingUnitRepository orderingUnitRepository;
	
	@Autowired
	private PhisicianRepository phisicianRepository;
	
	private ExampleMatcher exampleMatcher = ExampleMatcher
			.matching()
			.withIgnoreNullValues()
			.withIgnoreCase()
			.withMatcher("shortName", GenericPropertyMatchers.contains())
			.withMatcher("name", GenericPropertyMatchers.contains())
			.withMatcher("address.country", GenericPropertyMatchers.contains())
			.withMatcher("address.city", GenericPropertyMatchers.contains())
			.withMatcher("address.street", GenericPropertyMatchers.contains())
			.withIgnorePaths(
					"Id"
					);
	
	public OrderingUnitDTO createOrderingUnit(OrderingUnitDTO simpleOrderingUnitDTO) {
		var orderingUnit = modelMapper.map(simpleOrderingUnitDTO, OrderingUnit.class);
		return modelMapper.map(
				orderingUnitRepository.save(orderingUnit),
				OrderingUnitDTO.class
				);
	}
	
	public OrderingUnitDTO updateOrderingUnit(@NotNull String id, @NotNull OrderingUnitDTO simpleOrderingUnitDTO) throws EntityNotFoundException {
		var orderingUnit = orderingUnitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class));
		modelMapper.map(simpleOrderingUnitDTO, orderingUnit);
		return modelMapper.map(
				orderingUnitRepository.save(orderingUnit),
				OrderingUnitDTO.class
				);	
	}
	
	public List<OrderingUnitDTO> getOrderingUnitByExample(
			String shortName,
			String name,
			String country,
			String city,
			String street
			) {	
		var orderingUnit = new OrderingUnit();
		orderingUnit.setShortName(shortName);
		orderingUnit.setName(name);
		
		var address = new Address();
		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		
		orderingUnit.setAddress(address);
		return orderingUnitRepository.findAll(Example.of(orderingUnit, exampleMatcher))
				.stream()
				.map(obj -> modelMapper.map(obj, OrderingUnitDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public OrderingUnitDTO getOrderingUnitById(@NotNull String id) throws EntityNotFoundException {
		var orderingUnit = orderingUnitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class));
		return modelMapper.map(orderingUnit, OrderingUnitDTO.class);
	}
	
	public List<OrderingUnitDTO> getAll(){
		return orderingUnitRepository.findAll()
				.stream()
				.map(obj -> modelMapper.map(obj, OrderingUnitDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(value = "laboratoryTransactionManager", readOnly = true)
	public List<PhisicianDTO> getOrderingUnitPhisicians(@NotNull String orderingUnitId) throws EntityNotFoundException{
		return orderingUnitRepository
				.findById(orderingUnitId)
				.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class))
				.getPhisicians()
				.stream()
				.map(obj -> modelMapper.map(obj, PhisicianDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public void addOrderingUnitPhisician(@NotNull String orderingUnitId, @NotNull String phisicianId) throws EntityNotFoundException {
		var phisician = phisicianRepository
				.findById(phisicianId)
				.orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		orderingUnitRepository
		.findById(orderingUnitId)
		.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class))
		.addPhisician(phisician);
	
	}
	
	@Transactional(value = "laboratoryTransactionManager")
	public void removeOrderingUnitPhisician(@NotNull String orderingUnitId, @NotNull String phisicianId)  throws EntityNotFoundException {
		var phisician = phisicianRepository
				.findById(phisicianId)
				.orElseThrow(() -> new EntityNotFoundException(Phisician.class));
		orderingUnitRepository
		.findById(orderingUnitId)
		.orElseThrow(() -> new EntityNotFoundException(OrderingUnit.class))
		.removePhisician(phisician);

	}	
}
