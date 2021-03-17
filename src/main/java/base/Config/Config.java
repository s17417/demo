package base.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class Config {
	
	/*@Bean
	public ObjectMapper getJSONObjectMapper() {
		return new ObjectMapper();
	}*/
	
	@Bean
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
	
}
