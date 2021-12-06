package base.Config;

import java.beans.PropertyVetoException;
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

import com.mchange.v2.c3p0.ComboPooledDataSource;

import base.Model.baza.AdminRole;
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
		if (usersRepo.findByEmail("tomasz")==null) {
		Users user = new Users();
		Users user2 = new Users();
		
		user.setEmail("1@1.pl");
		user.setFirstname("tom");
		//user.setLogin("tomasz");
		user.setPassword("Game@122W");
		user.setSurname("gogo");
		user.setEnabled(true);
		
		user2.setEmail("tomasz.polawski@gmail.comdscdsc");
		user2.setFirstname("tom");
		user2.setPassword("Game@122W");
		user2.setSurname("gogo");
		
        
        Tenant tenant = new Tenant();
        tenant.setDatabseUserName("root");
        tenant.setDatabasePassword("root");
        tenant.setName("default_schema1");
       // tenant.setDatabasePassword(IdGenerator.getId().toString());
        UsersTenantRole usr = new UsersTenantRole(user,tenant,Role.SPECIFIC_DATABASE_TECHNICHAN);
        //UsersTenantRole usr1 = new UsersTenantRole(user,tenant,Role.APP_ADMIN);
        usrRepo.saveAll(Arrays.asList(usr/*,usr1*/));
        usersRepo.save(user2);
        
        System.out.println(usrRepo.findByUsersEmailAndTenantName("1@1.pl", "default_schema1"));
        System.out.println(usrRepo.findByUsersEmailAndTenantName("tomasz.polawski@gmail.com", "default_schema1"));
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
	
	/*Bean("usersDataSources")
	public ComboPooledDataSource ()*/
	
	
	@Bean(name="usersDataSource")
    @Primary
    public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		
		pooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("Lolita41bobo!");
		pooledDataSource.setJdbcUrl("jdbc:mysql://vifon41.hopto.org:3306/default_schema");
		pooledDataSource.setMaxPoolSize(2);
		pooledDataSource.setMaxIdleTime(10);
        return pooledDataSource;
    }

    /*@Bean(name="usersDataSource")
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://vifon41.hopto.org:3306/default_schema");
        dataSource.setUsername("root");
        dataSource.setPassword("Lolita41bobo!");
        
        return dataSource;
    }*/

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
        
        
        
        //properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "drop.sql");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//create-drop
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

        //properties.setProperty("hibernate.hbm2ddl.delimiter",";");
        //properties.setProperty("hibernate.hbm2dll.create_namespaces", "true");
        //properties.setProperty("javax.persistence.schema-generation.scripts.action", "drop-and-create");
        //properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "users-create.sql");
        //properties.setProperty("javax.persistence.schema-generation.scripts.drop-target", "users-drop.sql");
        
        properties.put(Environment.FORMAT_SQL, true);
	    properties.put(Environment.SHOW_SQL, true);
	    //properties.put(Environment.cac, properties)
	    properties.put(Environment.USE_QUERY_CACHE, true);
	    properties.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory");
	    properties.put(Environment.USE_SECOND_LEVEL_CACHE, true);
	    properties.put(Environment.CONNECTION_HANDLING, PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION);
	    //properties.put(Environment.POOL_SIZE, 15);
	   
	    //properties.put(Environment.AUTOCOMMIT, true);
	    //properties.put(Environment, properties)
	    //properties.put(Environment.C3P0_MIN_SIZE,2);
	    //properties.put(Environment.C3P0_MAX_SIZE,10);
        return properties;
    }

    
    
}