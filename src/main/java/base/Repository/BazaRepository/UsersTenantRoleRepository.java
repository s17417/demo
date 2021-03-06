package base.Repository.BazaRepository;

import java.util.Collection;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;

@Repository
public interface UsersTenantRoleRepository extends JpaRepository<UsersTenantRole, String> {

	public List<UsersTenantRole> findByUser(Users user);
	
	public List<UsersTenantRole> findByTenant(Tenant tenant);
	
	//@Cacheable("UsersTenants")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	@Query(value="SELECT usr FROM UsersTenantRole usr"
			+ " WHERE usr.user.email = :userEmail AND usr.tenant.name = :tenantName"
			)
	public List<UsersTenantRole> findByUsersEmailAndTenantName(@Param("userEmail") String userName,@Param("tenantName") String tenantName);
}
