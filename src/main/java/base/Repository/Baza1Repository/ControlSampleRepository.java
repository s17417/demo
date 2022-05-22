package base.Repository.Baza1Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.ControlSample;

@Repository
public interface ControlSampleRepository extends JpaRepository<ControlSample, String> {

	
	@Query("SELECT cs "
			+ "FROM ControlSample cs "
			+ "INNER JOIN FETCH cs.labTestOrders ore "
			+ "WHERE cs.Id=:controlSampleId AND order_Id=:orderId")
	public Optional<ControlSample> findByIdAndOrderIdWithLabTestOrders(String orderId, String controlSampleId);
	
	
	@Query("SELECT cs "
			+ "FROM ControlSample cs "
			+ "LEFT JOIN FETCH cs.specimentType st "
			+ "WHERE labQualityControl_Id=:orderId")
	public List<ControlSample> findAllByOrderId(String orderId);
	
	@Query("SELECT cs "
			+ "FROM ControlSample cs "
			+ "WHERE labQualityControl_Id=:orderId AND cs.Id=:controlSampleId")
	public Optional<ControlSample> findByOrderIdAndSampleId(String orderId, String controlSampleId);
}
