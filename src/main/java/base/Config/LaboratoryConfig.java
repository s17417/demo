package base.Config;

import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import base.Repository.Baza1Repository.PatientRepository;
import base.Utils.Multitenancy.DataSourceBasedMultiTenantConnectionProviderImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages="base.Repository.Baza1Repository",
		entityManagerFactoryRef = "laboratoryEntityManagerFactory",
		transactionManagerRef = "laboratoryTransactionManager"
		)
public class LaboratoryConfig {
	
	@Autowired
	@Lazy
	public PatientRepository patientRepo;
	
	@Autowired
	public CurrentTenantIdentifierResolver currentTenantIdentifierResolverImpl;
	
	@Autowired
	public DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProviderImpl;
	
	/*@EventListener
    public void appReady(ApplicationReadyEvent event) {
		Patient patient = new Patient();
		patient.setName("tomasz");
		patient.setSurname("game");
        patientRepo.save(patient);
    }*/
	
	 
	
	@Bean(name="laboratoryEntityManagerFactory")
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("laboratoryDataSource") DataSource dataSource) {		
		LocalContainerEntityManagerFactoryBean entityManagerFactory= builder
	    		  .dataSource(dataSource)
	    		  .properties(
	    				  additionalProperties()
	    				  .entrySet()
	    				  .stream()
	    				  .map(e -> e)
	    				  .collect(
	    						  Collectors
	    						  .toMap( k -> k.getKey().toString(), v-> v.getValue())
	    						  )
	    				  )
	    		  .packages("base.Model.baza1")
	    		  .persistenceUnit("laboratory")
	    		  .build();
		
		return entityManagerFactory;
	    
	   }
	
	@Bean (name="laboratoryDataSource")
    public DriverManagerDataSource defaultDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setUrl("jdbc:mysql://localhost:3306/");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://vifon41.hopto.org:3306/empty"); //uncomment on production
        dataSource.setUsername("root");
        dataSource.setPassword("Lolita41bobo!");
        
        
        return dataSource;
    }
	
	
	@Bean(name="laboratoryTransactionManager")
    public PlatformTransactionManager hibernateTransactionManager(@Qualifier("laboratoryEntityManagerFactory") EntityManagerFactory emf) {
    	 JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(emf);

         return transactionManager;
     }
	
	  private Properties additionalProperties() {
	        Properties properties = new Properties();
	        
	        properties.setProperty("hibernate.hbm2ddl.delimiter",";");
	        properties.setProperty("hibernate.hbm2dll.create_namespaces", "true");
	        properties.setProperty("javax.persistence.schema-generation.scripts.action", "drop-and-create");
	        properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "laboratory-create.sql");
	        properties.setProperty("javax.persistence.schema-generation.scripts.drop-target", "laboratory-drop.sql");
	        
	        properties.setProperty(Environment.HBM2DDL_AUTO, "none");
	        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
	        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
	        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
	        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, dataSourceBasedMultiTenantConnectionProviderImpl);
	        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolverImpl);
	        properties.put(Environment.FORMAT_SQL, true);
	        properties.put(Environment.SHOW_SQL, true);
		    properties.put(Environment.CONNECTION_HANDLING, PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION);
		    properties.put(Environment.POOL_SIZE, 15);
		    //properties.put(Environment.USE_QUERY_CACHE,  Boolean.TRUE.toString());

		    //properties.put(Environment.AUTOCOMMIT, true);

	        return properties;
	    }

}
