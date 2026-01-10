package ml.tamboura.Bibliotheque.dto;

public class AuthResponse {

    private String token;

    // Constructeurs
    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
