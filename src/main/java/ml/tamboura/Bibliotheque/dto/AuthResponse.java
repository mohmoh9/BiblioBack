package ml.tamboura.Bibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String role;
    private String name;
}
