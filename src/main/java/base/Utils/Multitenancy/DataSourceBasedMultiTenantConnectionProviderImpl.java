package base.Utils.Multitenancy;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

	//private Map<String, DataSource> map= new HashMap<>();
	
	
	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		if(tenantIdentifier.equals(TokenConstant.EMPTY_TOKEN_TENANT_FIELD.getValue())) 
			return selectAnyDataSource();
		Tenant tenant=tenantDetailServiceImpl.getTenantByName(tenantIdentifier);
		if (tenant==null)return selectAnyDataSource();
		var conn= dataSourceGenerator.createTenantDataSource(tenant);
			return conn;
	}	

	 public DataSource dataSource() {
	     return dataSource;
	 }

}
