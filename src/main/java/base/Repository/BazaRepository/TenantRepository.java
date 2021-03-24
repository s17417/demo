package base.Repository.BazaRepository;

import javax.persistence.QueryHint;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import base.Model.baza.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
	
	@QueryHints({@QueryHint(name = "org.hibernate.cacheRegion", value ="TenantCache"), @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public Tenant findByName(String name);


	
	
}
