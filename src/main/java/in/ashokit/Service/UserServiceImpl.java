package in.ashokit.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.Entity.Users;
import in.ashokit.Repo.UserRepo;

@Service
public class UserServiceImpl {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	public void registerUser(Users user) {
		user.setRole("ROLE_USER");
	String encodedPassword	=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
	}
	
}
