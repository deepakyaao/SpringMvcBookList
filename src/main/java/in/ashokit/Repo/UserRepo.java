package in.ashokit.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.Entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);
	
}
