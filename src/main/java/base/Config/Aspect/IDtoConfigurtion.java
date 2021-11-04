package base.Config.Aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;


public interface IDtoConfigurtion {
	
	@AfterReturning(value = "Poincuts.mapperDtoConfiguration()", returning = "mapper")
	void modelMapperConfiguration(ModelMapper mapper);

}