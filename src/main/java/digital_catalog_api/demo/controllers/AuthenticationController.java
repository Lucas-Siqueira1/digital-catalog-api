package digital_catalog_api.demo.controllers;

import digital_catalog_api.demo.models.dto.AuthenticationDto;
import digital_catalog_api.demo.models.dto.LoginResponseDto;
import digital_catalog_api.demo.models.entities.UserAdmin;
import digital_catalog_api.demo.repositories.UserAdminRepository;
import digital_catalog_api.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserAdmin) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
