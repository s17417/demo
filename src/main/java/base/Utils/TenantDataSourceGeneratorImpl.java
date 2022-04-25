package base.Utils;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import base.Model.baza.Tenant;



//@CacheConfig(cacheNames = "tenantDataSource")
@Component
public class TenantDataSourceGeneratorImpl implements IDataSourceGenerator {

	private String databaseLogin;
	private String databasePassword;
	
	public TenantDataSourceGeneratorImpl(@Value("${databaseLogin}") String databaseLogin,@Value("${databasePassword}") String databasePassword){
		this.databaseLogin=databaseLogin;
		this.databasePassword=databasePassword;
		
	}
	
	@Cacheable(cacheNames = "tenantDataSource",sync = true)
	@Override
	public DataSource createTenantDataSource(Tenant tenant)  {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
			dataSource.setDriverClass(tenant.getDriverClassName());
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
        dataSource.setJdbcUrl(tenant.getDatabaseFullUrl());
        dataSource.setUser(tenant.getDatabseUserName());
        dataSource.setPassword(tenant.getDatabasePassword());
        dataSource.setInitialPoolSize(1);
        dataSource.setMaxPoolSize(4);
		dataSource.setMinPoolSize(0);
		dataSource.setMaxIdleTime(240);
		dataSource.setAcquireIncrement(1);
		return dataSource;
	}
	
	/*@CacheEvict()
	public void evictcachesAtIntervals() {
	    evictAllCaches();
	}*/
	
	
	/*@Override
	public DataSource createTenantDataSource(Tenant tenant) {
		
		DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName(tenant.getDriverClassName());
        dataSource.setUrl(tenant.getDatabaseFullUrl());
        dataSource.setUsername(tenant.getDatabseUserName());
        dataSource.setPassword(tenant.getDatabasePassword());
		return dataSource;
	}*/
	
	private DataSource createRootDataSource(Tenant tenant) {
		var dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName(tenant.getDriverClassName());
        dataSource.setUrl(tenant.getDatabaseFullUrl()+"?createDatabaseIfNotExist=true");
        dataSource.setUsername(databaseLogin);
        dataSource.setPassword(databasePassword);
        return dataSource;
	}
	
	@Transactional
	@Override
	public void tenantDatabaseInitialization(Tenant tenant) {
		var createUserStatement =
				"CREATE USER '"+
						tenant.getDatabseUserName()+
						"'@'%' IDENTIFIED BY '"+
						tenant.getDatabasePassword()+
						"'";
		
        var grantPriviligiesStatement = 
        		"GRANT INSERT,DELETE,SELECT,UPDATE ON "+
        				tenant.getDatabseUserName()+
        				". * TO '"+
        				tenant.getDatabseUserName()+
        				"'@'%'; FLUSH PRIVILEGES";
		
        var resource = new ResourceDatabasePopulator(
        		new ByteArrayResource(createUserStatement.getBytes()),
        		new ByteArrayResource(grantPriviligiesStatement.getBytes()),
        		new ClassPathResource("scripts/laboratory-create.sql")
        		);
       
        DatabasePopulatorUtils.execute(resource, createRootDataSource(tenant));   
	}
	@Override
	public void tenantDatabaseDeletion(Tenant tenant) {
		var dropUserStatement =
				"DROP USER '"+tenant.getDatabseUserName()+"'";
		var dropDatabaseStatement = 
				"DROP DATABASE "+tenant.getDatabseUserName()+"";
		
		var resource = new ResourceDatabasePopulator(
	       		new ByteArrayResource(dropDatabaseStatement.getBytes()),
	       		new ByteArrayResource(dropUserStatement.getBytes())
	       		);
		resource.setContinueOnError(true);
		
        DatabasePopulatorUtils.execute(resource, createRootDataSource(tenant));  		
		
	}

}
