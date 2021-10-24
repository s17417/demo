package base.Utils;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Tenant;



@Component
public class TenantDataSourceGeneratorImpl implements IDataSourceGenerator {

	private String databaseLogin;
	private String databasePassword;
	
	
	public TenantDataSourceGeneratorImpl(@Value("${databaseLogin}") String databaseLogin,@Value("${databasePassword}") String databasePassword){
		this.databaseLogin=databaseLogin;
		this.databasePassword=databasePassword;
		
	}
	@Override
	public DataSource createTenantDataSource(Tenant tenant) {
		
		DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName(tenant.getDriverClassName());
        dataSource.setUrl(tenant.getDatabaseFullUrl());
        dataSource.setUsername(tenant.getDatabseUserName());
        dataSource.setPassword(tenant.getDatabasePassword());
		return dataSource;
	}
	
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
