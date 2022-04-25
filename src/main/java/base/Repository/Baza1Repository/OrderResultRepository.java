package base.Repository.Baza1Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.OrderResult;

@Repository
public interface OrderResultRepository extends JpaRepository<OrderResult, String>,  JpaSpecificationExecutor<OrderResult> {
	
	@Query("SELECT o "
			+ "FROM OrderResult o "
			+ "INNER JOIN FETCH o.patientSample ps "
			+ "INNER JOIN FETCH ps.order ord "
			+ "WHERE o.Id=:orderResultId "
				+ "AND ord.Id=:patientOrderId "
				+ "AND ps.Id=:patientSampleId")
	public Optional<OrderResult> findByIdAndOrder(String patientOrderId, String patientSampleId, String orderResultId);

	@Query("SELECT o "
			+ "FROM OrderResult o "
			+ "LEFT JOIN FETCH o.laboratoryTest lt "
			+ "INNER JOIN FETCH o.patientSample ps "
			+ "INNER JOIN ps.order ord "
			+ "WHERE ord.Id=:orderId")
	public List<OrderResult> findAllByOrderId(String orderId);
}
