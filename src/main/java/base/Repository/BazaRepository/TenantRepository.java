package base.Repository.BazaRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
	
	@Cacheable("Tenants")
	public Tenant findByName(String name);
}
