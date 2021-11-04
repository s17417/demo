package base.Config;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;

import base.Config.Aspect.IDtoConfigurtion;
import base.Config.Aspect.PersistentAuditableDtoConfiguration;


@Configuration
public class Config {
	
	@Bean
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	}

	@Bean
	public ModelMapper modelMapper() {
		var mapper=new ModelMapper();
		mapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STRICT).setImplicitMappingEnabled(false);	
	    return mapper;
	}
	
	@Bean
	public Converter<String,String> toLowerCaseConverter(){ 
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().toLowerCase() : null;
	}
	
	@Bean
	public Converter<String,String> firstLetterUpperCaseConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().substring(0, 1).toUpperCase() + mapper.getSource().strip().toLowerCase().substring(1) : null;
	}
	
	@Bean
	public Converter<String,String> stripConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip():null;
	}
	
	@Bean
	public Condition<String, String> stringNotNullCondition(){
		return condition -> condition.getSource()!=null;
	}
	
	/*@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public IDtoConfigurtion persistentAuditableDtoConfiguration() {
		return new PersistentAuditableDtoConfiguration();
	}
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public IDtoConfigurtion usersDtoConfiguration() {
		return new PersistentAuditableDtoConfiguration();
	}*/
}
