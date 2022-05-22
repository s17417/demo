package base.Repository.Baza1Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.LabQualityControlResult;

@Repository
public interface LabQualityControlResultRepository extends JpaRepository<LabQualityControlResult, String> {
	
	@Query("SELECT cr "
			+ "FROM LabQualityControlResult cr "
			+ "INNER JOIN FETCH cr.controlSample cs "
			+ "INNER JOIN FETCH cs.order ord "
			+ "WHERE cr.Id=:controlResultId "
				+ "AND ord.Id=:controlOrderId "
				+ "AND cs.Id=:controlSampleId")
	public Optional<LabQualityControlResult> findByIdAndOrder(String controlOrderId, String controlSampleId, String controlResultId);

	
	@Query("SELECT cr "
			+ "FROM LabQualityControlResult cr "
			+ "LEFT JOIN FETCH cr.laboratoryTest lt "
			+ "INNER JOIN FETCH cr.controlSample cs "
			+ "INNER JOIN cs.order ord "
			+ "WHERE ord.Id=:orderId")
	public List<LabQualityControlResult> findAllByOrderId(String orderId);
	
	
	@Query("SELECT cr "
			+ "FROM LabQualityControlResult cr "
			+ "LEFT JOIN FETCH cr.laboratoryTest lt "
			+ "INNER JOIN FETCH cr.controlSample cs "
			+ "INNER JOIN FETCH cs.order ord "
			+ "WHERE ord.Id=:controlOrderId "
			+ "AND cs.Id=:controlSampleId "
			+ "AND lt.Id=:laboratoryTestId "
			+ "AND cr.targetValue=true")
	public Optional<LabQualityControlResult> findTargetValue(String controlOrderId, String controlSampleId, String laboratoryTestId);
}
