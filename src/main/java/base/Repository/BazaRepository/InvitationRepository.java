package base.Repository.BazaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import base.Model.baza.Invitation;
import base.Model.baza.Users;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, String> {
	
	@Query("SELECT i FROM Invitation i LEFT JOIN FETCH i.tenant WHERE i.email=:email")
	public Invitation findByEmail (@Param("email") String email);

}
