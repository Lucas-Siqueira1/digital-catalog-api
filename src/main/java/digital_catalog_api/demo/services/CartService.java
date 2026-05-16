package digital_catalog_api.demo.services;
import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    UserAdminRepository userAdminRepository;

    public UserAdmin insert(UserAdmin newUser) {
        User
    }
}
