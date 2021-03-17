package base.Repository.BazaRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	
	@EntityGraph(value = "Users.usersTenantRole")
	public Users findByName (String name);

}
