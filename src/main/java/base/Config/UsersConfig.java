package base.Config;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import base.Model.baza.Role;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Repository.BazaRepository.UsersTenantRoleRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages="base.Repository.BazaRepository",
		entityManagerFactoryRef = "usersEntityManagerFactory",
		transactionManagerRef = "usersTransactionManager"
		)
public class UsersConfig {
	
	@Autowired
	@Lazy
	public UsersRepository usersRepo;
	
	@Autowired
	@Lazy
	public UsersTenantRoleRepository usrRepo;
	
	@Autowired
	@Lazy
	public TenantRepository tenantRepo;
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		if (usersRepo.findByName("tomasz")==null) {
		Users user = new Users();
		user.setEmail("1@1.pl");
		user.setFirstname("tom");
		user.setName("tomasz");
		user.setPassword("Game@122W");
		user.setSurname("gogo");
		
        
        Tenant tenant = new Tenant();
        tenant.setDatabseUserName("root");
        tenant.setDatabasePassword("root");
        tenant.setName("default_schema1");
       // tenant.setDatabasePassword(IdGenerator.getId().toString());
        UsersTenantRole usr = new UsersTenantRole(user,tenant,Role.BASIC_USER);
        UsersTenantRole usr1 = new UsersTenantRole(user,tenant,Role.APP_ADMIN);
        usrRepo.saveAll(Arrays.asList(usr,usr1));
        
        System.out.println(usrRepo.findByUsersNameAndTenantName("tomasz", "default_schema1"));
		}
    }
	
	@Bean(name="usersEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("usersDataSource")DataSource dataSource) {
		return builder
	    		  .dataSource(dataSource)
	    		  .properties(
	    				  additionalProperties()
	    				  .entrySet()
	    				  .stream()
	    				  .map(e -> e)
	    				  .collect(
	    						  Collectors
	    						  .toMap( k -> k.getKey().toString(), v-> v.getValue().toString())
	    						  )
	    				  )
	    		  .packages("base.Model.baza")
	    		  .persistenceUnit("users")
	    		  .build();
	   }
	
	

    @Bean(name="usersDataSource")
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/default_schema");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        return dataSource;
    }

    @Bean(name="usersTransactionManager")
    @Primary
    public PlatformTransactionManager hibernateTransactionManager(@Qualifier("usersEntityManagerFactory")LocalContainerEntityManagerFactoryBean emf) {
    	 JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(emf.getObject());

         return transactionManager;
     }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//create-drop
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.put(Environment.FORMAT_SQL, true);
	    properties.put(Environment.SHOW_SQL, true);
	    properties.put(Environment.USE_QUERY_CACHE,  Boolean.TRUE.toString());
	    properties.put(Environment.CONNECTION_HANDLING, PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION);
	    properties.put(Environment.POOL_SIZE, 15);
        
        return properties;
    }

    
    
}