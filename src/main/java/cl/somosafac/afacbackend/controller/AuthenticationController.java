package cl.somosafac.afacbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> cambiarContrasena(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordRequest request) {
        String correo = jwtService.getCorreoFromToken(token.substring(7));
        authenticationService.cambiarContrasena(correo, request);
        return ResponseEntity.ok().build();
    }
} 