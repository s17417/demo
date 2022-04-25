package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.SpecimentType;

@Repository
public interface SpecimentTypeRepository extends JpaRepository<SpecimentType, String> {
	
	@Modifying
	@Query("update SpecimentType sp set sp.isActive=:isActive where sp.Id=:id")
	public void activeStatus(String id, Boolean isActive);

}
