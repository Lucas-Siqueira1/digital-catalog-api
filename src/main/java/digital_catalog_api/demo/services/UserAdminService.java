package digital_catalog_api.demo.services;
import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService {

    @Autowired
    UserAdminRepository userAdminRepository;

    public UserAdmin insert(UserAdmin newUser) {
        return userAdminRepository.save(newUser);
    }

    public void updatePassword(String password, UserAdmin user) {
        user.setPassword(password);
        userAdminRepository.save(user);
    }
}
