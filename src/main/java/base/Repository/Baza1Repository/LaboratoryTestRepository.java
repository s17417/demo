package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.Model.baza1.LaboratoryTest;

@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, String> {

}
