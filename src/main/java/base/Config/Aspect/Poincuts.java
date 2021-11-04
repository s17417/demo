package base.Config.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Poincuts {

	@Pointcut("execution (* base.Config.Config.modelMapper(..))")
	public void mapperDtoConfiguration() {}

}
