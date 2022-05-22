package base.Repository.Baza1Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.LabQualityControlResult;
import base.Model.baza1.Method;
import base.Model.baza1.OrderResult;

@Repository
public interface AnalyteResultRepository extends JpaRepository<AbstractAnalyteResult<?,? extends Method>, String> {

	/*@Query("SELECT ar FROM AbstractAnalyteResult ar "
			+ "JOIN FETCH ar.method met "
			//+ "JOIN FETCH QuantitativeFormat Method metQan ON met.result "
			//+ "JOIN FETCH met.analyte an "
			+ "INNER JOIN ar.labTestOrder ord "
			+ "INNER JOIN OrderResult ore ON ord.Id=ore.Id "
			+ "INNER JOIN ore.patientSample ps "
			+ "INNER JOIN ps.order po "
			+ "WHERE po.Id=:patientOrderId AND ord.Id=:orderResultId"
			)
	*/
	
	@Query("SELECT ar FROM AbstractAnalyteResult ar "
			+ "JOIN FETCH ar.method met "
			+ "JOIN FETCH met.analyte an "
			+ "INNER JOIN OrderResult ore ON labTestOrder_Id=ore.Id "
			+ "WHERE ore.Id=:orderResultId "
			+ "AND patientSample_Id "
			+ "IN (SELECT ps.Id FROM PatientSample ps WHERE patientOrder_Id=:patientOrderId)"
			)
	public List<? extends AbstractAnalyteResult<?,? extends Method>> findAnalyteResultsByPatientOrderIdAndOrderResultId(String patientOrderId, String orderResultId);
	
	@Query("SELECT ore FROM OrderResult ore "
			+ "JOIN FETCH ore.laboratoryTest lbt "
			+ "JOIN FETCH ore.analyteResults ar "
			+ "JOIN FETCH ar.method met "
			+ "JOIN FETCH met.analyte an "
			+ "JOIN FETCH ore.patientSample pt "
			+ "JOIN FETCH pt.order ord "
			+ "WHERE ore.Id=:orderResultId "
			+ "AND ord.Id=:patientOrderId"
			)
	public Optional<OrderResult> findAnalyteResultsByPatientOrderIdAndOrderResultIdWithPatientOrder(String patientOrderId, String orderResultId);
	
	@Query("SELECT ore FROM LabQualityControlResult ore "
			+ "JOIN FETCH ore.laboratoryTest lbt "
			+ "JOIN FETCH ore.analyteResults ar "
			+ "JOIN FETCH ar.method met "
			+ "JOIN FETCH met.analyte an "
			+ "JOIN FETCH ore.controlSample pt "
			+ "JOIN FETCH pt.order ord "
			+ "WHERE ore.Id=:controlResultId "
			+ "AND ord.Id=:controlOrderId"
			)
	public Optional<LabQualityControlResult> findAnalyteResultsByControlOrderIdAndControlResultId(String controlOrderId, String controlResultId);
	

}
