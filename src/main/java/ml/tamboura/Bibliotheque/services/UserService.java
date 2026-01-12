package ml.tamboura.Bibliotheque.services;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.UserRequest;
import ml.tamboura.Bibliotheque.dto.UserResponse;
import ml.tamboura.Bibliotheque.entity.Role;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.repository.UserRepository;
import ml.tamboura.Bibliotheque.security.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // CREATE
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();


        return mapToResponse(userRepository.save(user));
    }

    // READ ALL
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ ONE
    public UserResponse getUserById(Long id) {
        return mapToResponse(findUser(id));
    }

    // UPDATE
    public UserResponse updateUser(Long id, UserRequest request) {

        User user = findUser(id);

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return mapToResponse(userRepository.save(user));
    }

    // DELETE
    public void deleteUser(Long id) {
        userRepository.delete(findUser(id));
    }

    // 🔧 UTIL
    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non authentifié"));
    }

    public UserResponse getCurrentUserProfile() {
        return mapToResponse(getCurrentUser());
    }

}
