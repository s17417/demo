package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.Method;

@Repository
public interface MethodRepository extends JpaRepository<Method, String> {

}
