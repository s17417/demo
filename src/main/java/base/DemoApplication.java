package base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
public class DemoApplication {
	public static ConfigurableApplicationContext context;
	public static String[]args1;
	public static SpringApplication appCtxBuilder = new SpringApplicationBuilder(DemoApplication.class).build();
	
	public static void main(String[] args) {
		DemoApplication.args1=args;
		context=appCtxBuilder.run(args);
	}
	
	/**
	 * Restarts application closing context, releasing all resources and locks that the implementation might hold.
	 * <br>Starts a new non-Daemon Thread in which the actual context is closed and runs a new context.
	 */
	public static void restart() {
		Thread thread= new Thread ( () ->{
			context.close();
			context=appCtxBuilder.run(args1);
		});
		thread.setDaemon(false);
		thread.start();
	}
}
