package base.Utils.Multitenancy;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import base.Model.baza.Tenant;
import base.Services.baza.ITenantDetailService;
import base.Utils.IDataSourceGenerator;
import base.Utils.Security.TokenConstant;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ITenantDetailService tenantDetailServiceImpl;
	
	private DriverManagerDataSource dataSource;
	
	private IDataSourceGenerator dataSourceGenerator;
	
	public DataSourceBasedMultiTenantConnectionProviderImpl(@Lazy ITenantDetailService tenantDetailServiceImpl,
			@Qualifier("laboratoryDataSource") DriverManagerDataSource dataSource, IDataSourceGenerator dataSourceGenerator) {
		this.tenantDetailServiceImpl = tenantDetailServiceImpl;
		this.dataSource = dataSource;
		this.dataSourceGenerator=dataSourceGenerator;
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return dataSource();
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		if(tenantIdentifier.equals(TokenConstant.EMPTY_TOKEN_TENANT_FIELD)) 
			return selectAnyDataSource();
		Tenant tenant=tenantDetailServiceImpl.getTenantByName(tenantIdentifier);
		if (tenant==null)return selectAnyDataSource();
			/*DriverManagerDataSource dataSource =  new DriverManagerDataSource();
	        dataSource.setDriverClassName(tenant.getDriverClassName());
	        dataSource.setUrl(tenant.getDatabaseFullUrl());
	        dataSource.setUsername(tenant.getDatabseUserName());
	        dataSource.setPassword(tenant.getDatabasePassword());*/
	    
	    return dataSourceGenerator.createTenantDataSource(tenant); 
		//return dataSource;
	}	

	 public DataSource dataSource() {
	     return dataSource;
	 }

}
