package base.Repository.Baza1Repository;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.PatientOrder;

@Repository
public interface PatientOrderRepository extends JpaRepository<PatientOrder, String>, JpaSpecificationExecutor<PatientOrder> {
	
	@Query("SELECT pOrd "
			+ "FROM PatientOrder pOrd "
			+ "LEFT JOIN FETCH pOrd.patient "
			+ "LEFT JOIN FETCH pOrd.phisician "
			+ "LEFT JOIN FETCH pOrd.orderingUnit "
			+ "WHERE pOrd.Id=:patientOrderId")
	public Optional<PatientOrder> findPatientOrderById(@NotNull String patientOrderId);
	
}
