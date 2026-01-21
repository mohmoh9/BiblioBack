package ml.tamboura.Bibliotheque.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.UpdateProfileRequest;
import ml.tamboura.Bibliotheque.dto.UserRequest;
import ml.tamboura.Bibliotheque.dto.UserResponse;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.security.CustomUserDetails;
import ml.tamboura.Bibliotheque.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    // READ ALL
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        User updatedUser = userService.updateProfile(userDetails.getUser(), request);
        return ResponseEntity.ok(updatedUser);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
