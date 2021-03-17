package base.Config;

import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
        dataSource.setUrl("jdbc:mysql://localhost:3306/");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setUrl("jdbc:mysql://localhost:3306/default_schema1");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        
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
	        properties.setProperty(Environment.HBM2DDL_AUTO, "none");
	        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
	        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
	        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
	        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, dataSourceBasedMultiTenantConnectionProviderImpl);
	        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolverImpl);
	       // properties.put(Environment.FORMAT_SQL, true);
	       // properties.put(Environment.SHOW_SQL, true);
	        
	        return properties;
	    }

}
