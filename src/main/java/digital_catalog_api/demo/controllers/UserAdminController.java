package digital_catalog_api.demo.controllers;

import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.services.UserAdminService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserAdminController {

    private final UserAdminService userAdminService;

    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @PostMapping
    public ResponseEntity<UserAdmin> insert(@RequestBody UserAdmin newUser) {
        UserAdmin user = userAdminService.insert(newUser);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<Void> updatePassword(String password, @RequestBody UserAdmin user) {
        userAdminService.updatePassword(password, user);
        return ResponseEntity.noContent().build();
    }
}
