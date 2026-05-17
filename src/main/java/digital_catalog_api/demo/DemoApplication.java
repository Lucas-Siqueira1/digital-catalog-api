package digital_catalog_api.demo;

import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.models.entities.enums.Role;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	@Bean
	public CommandLineRunner seedAdmin(UserAdminRepository repository, PasswordEncoder encoder) {
		return args -> {
			if (repository.findByEmail(adminEmail).isEmpty()) {
				UserAdmin admin = new UserAdmin();
				admin.setEmail(adminEmail);
				admin.setPassword(encoder.encode(adminPassword));
				admin.setRole(Role.ADMIN);
				repository.save(admin);
				System.out.println("Admin criado com sucesso!");
			}
		};
	}

}
