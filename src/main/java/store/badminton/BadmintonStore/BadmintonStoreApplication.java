package store.badminton.BadmintonStore;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import store.badminton.BadmintonStore.repositories.RoleRepo;
import store.badminton.BadmintonStore.repositories.UserRepo;
@SpringBootApplication
public class BadmintonStoreApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {

		SpringApplication.run(BadmintonStoreApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {


//		try {
//
//			Role role = new Role();
//			role.setName("ROLE_ADMIN");
//
//			Role role1 = new Role();
//			role1.setName("ROLE_USER");
//
//			List<Role> roles = new ArrayList<>();
//			roles.add(role);
//			roles.add(role1);
//
//			User user = new User();
//			user.setUsername("admin");
//			user.setPassword(this.passwordEncoder.encode("123"));
//			user.setRoles(roles);
//			userRepo.save(user);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}

	}

}
