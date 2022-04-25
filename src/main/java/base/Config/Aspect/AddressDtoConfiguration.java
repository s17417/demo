package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.AddressDTO;
import base.Model.baza1.Address;

@Component
@Aspect
@Order(0)
public class AddressDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(Address.class, AddressDTO.class).implicitMappings();
		mapper.createTypeMap(AddressDTO.class, Address.class, DTOObjectConstans.CREATE.name()).addMappings(map -> {
			map.map(AddressDTO::getCountry, Address::setCountry);
			map.map(AddressDTO::getState, Address::setState);
			map.map(AddressDTO::getCity, Address::setCity);
			map.map(AddressDTO::getStreet, Address::setStreet);
			map.map(AddressDTO::getPostalCode, Address::setPostalCode);
		});
		
		mapper.createTypeMap(AddressDTO.class, Address.class).addMappings(map -> {
			map.map(AddressDTO::getCountry, Address::setCountry);
			map.map(AddressDTO::getState, Address::setState);
			map.map(AddressDTO::getCity, Address::setCity);
			map.map(AddressDTO::getStreet, Address::setStreet);
			map.map(AddressDTO::getPostalCode, Address::setPostalCode);
		});
		
	}

}
