package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import base.Model.baza1.Phisician;

@Repository
public interface PhisicianRepository extends JpaRepository<Phisician, String> {
	
}
