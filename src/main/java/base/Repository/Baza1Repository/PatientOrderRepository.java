package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import base.Model.baza1.PatientOrder;

@Repository
public interface PatientOrderRepository extends JpaRepository<PatientOrder, String>, JpaSpecificationExecutor<PatientOrder> {

}
