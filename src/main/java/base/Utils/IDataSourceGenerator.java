package base.Utils;

import javax.sql.DataSource;

import base.Model.baza.Tenant;

public interface IDataSourceGenerator {

	DataSource createTenantDataSource(Tenant tenant);

	void tenantDatabaseInitialization(Tenant tenant);

	void tenantDatabaseDeletion(Tenant tenant);

}