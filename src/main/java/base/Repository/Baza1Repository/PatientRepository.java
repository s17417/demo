package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza1.Patient;

@Repository
//@Transactional("laboratoryTransactionManager")
public interface PatientRepository extends JpaRepository<Patient, Long> {
	public Patient findByName(String name);
}
