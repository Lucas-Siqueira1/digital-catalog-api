package digital_catalog_api.demo.repositories;

import digital_catalog_api.demo.models.entities.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, UUID> {
    UserDetails findByEmail(String email);
}
