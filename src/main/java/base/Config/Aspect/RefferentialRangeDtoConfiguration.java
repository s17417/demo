package base.Config.Aspect;

import java.math.BigDecimal;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.RefferentialRangeDTO;
import base.Model.baza1.Address;
import base.Model.baza1.RefferentialRange;

@Component
@Aspect
@Order(0)
public class RefferentialRangeDtoConfiguration implements IDtoConfigurtion {

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper.createTypeMap(RefferentialRange.class, RefferentialRangeDTO.class).implicitMappings();
		
		/*mapper.createTypeMap(RefferentialRangeDTO.class, RefferentialRange.class, DTOObjectConstans.CREATE.name())
			.implicitMappings();*/
		
		mapper.createTypeMap(RefferentialRangeDTO.class, RefferentialRange.class)
		.implicitMappings();
	}

}
