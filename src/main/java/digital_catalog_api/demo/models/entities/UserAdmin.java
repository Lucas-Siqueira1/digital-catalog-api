package digital_catalog_api.demo.models.entities;

import digital_catalog_api.demo.models.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAdmin userAdmin = (UserAdmin) o;
        return Objects.equals(id, userAdmin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
