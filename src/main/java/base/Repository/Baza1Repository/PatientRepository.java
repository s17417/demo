package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import base.Model.baza1.Patient;

@Repository
//@Transactional("laboratoryTransactionManager")
public interface PatientRepository extends JpaRepository<Patient, String>, JpaSpecificationExecutor<Patient> {
	public Patient findByName(String name);
	
}
