package ml.tamboura.Bibliotheque.coltroller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.AuthResponse;
import ml.tamboura.Bibliotheque.dto.LoginRequest;
import ml.tamboura.Bibliotheque.dto.RegisterRequest;
import ml.tamboura.Bibliotheque.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

        private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
        public ResponseEntity<AuthResponse> register(
                @RequestBody RegisterRequest request) {

            return ResponseEntity.ok(authService.register(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(
                @RequestBody LoginRequest request) {

            return ResponseEntity.ok(authService.login(request));
        }
}
