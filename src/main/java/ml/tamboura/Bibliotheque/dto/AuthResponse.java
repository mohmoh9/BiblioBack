package ml.tamboura.Bibliotheque.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {

    private String token;

    // Constructeurs
    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

}
