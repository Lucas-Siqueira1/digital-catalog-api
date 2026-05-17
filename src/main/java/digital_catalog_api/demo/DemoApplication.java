package digital_catalog_api.demo;

import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.models.entities.enums.Role;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedAdmin(UserAdminRepository repository, PasswordEncoder encoder) {
		return args -> {
			if (repository.findByEmail("admin@loja.com").isEmpty()) {
				UserAdmin admin = new UserAdmin();
				admin.setEmail("admin@loja.com");
				admin.setPassword(encoder.encode("senha123"));
				admin.setRole(Role.ADMIN);
				repository.save(admin);
				System.out.println("Admin criado com sucesso!");
			}
		};
	}

}
