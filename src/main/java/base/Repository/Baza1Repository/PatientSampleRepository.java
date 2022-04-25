package base.Repository.Baza1Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import base.Model.baza1.PatientSample;

public interface PatientSampleRepository extends JpaRepository<PatientSample, String> {
	
	@Query("SELECT ps "
			+ "FROM PatientSample ps "
			+ "INNER JOIN FETCH ps.labTestOrders ore "
			+ "WHERE ps.Id=:patientSampleId AND order_Id=:orderId")
	public Optional<PatientSample> findByIdAndOrderIdWithLabTestOrders(String orderId, String patientSampleId);
	
	
	@Query("SELECT ps "
			+ "FROM PatientSample ps "
			+ "LEFT JOIN FETCH ps.specimentType st "
			+ "WHERE patientOrder_Id=:orderId")
	public List<PatientSample> findAllByOrderId(String orderId);
	
	@Query("SELECT ps "
			+ "FROM PatientSample ps "
			+ "WHERE patientOrder_Id=:orderId AND ps.Id=:patientSampleId")
	public Optional<PatientSample> findByOrderIdAndSampleId(String orderId, String patientSampleId);

}
