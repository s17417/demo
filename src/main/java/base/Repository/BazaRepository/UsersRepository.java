package base.Repository.BazaRepository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import base.Model.baza.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
	
	@Query("SELECT u FROM Users u LEFT JOIN FETCH u.usersTenantRole utr LEFT JOIN FETCH utr.tenant WHERE u.name=:name")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheRegion", value ="UsersQueryCache"), @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value ="true") })
	public Users findByName (@Param("name") String name);

}
