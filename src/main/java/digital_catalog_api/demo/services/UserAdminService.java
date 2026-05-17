package digital_catalog_api.demo.services;
import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService implements UserDetailsService {

    @Autowired
    UserAdminRepository userAdminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAdmin insert(UserAdmin newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userAdminRepository.save(newUser);
    }

    public void updatePassword(String password, UserAdmin user) {
        user.setPassword(passwordEncoder.encode(password));
        userAdminRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAdminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
