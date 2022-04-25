package base.Repository.Baza1Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import base.Model.baza1.AbstractComment;
import base.Model.baza1.PatientComment;

@Repository
public interface CommentRepository extends JpaRepository<PatientComment, String> {
	

}
