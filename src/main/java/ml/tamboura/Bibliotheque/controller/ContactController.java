package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.ContactRequest;
import ml.tamboura.Bibliotheque.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactRequest request) {
        service.saveMessage(request);
        return ResponseEntity.ok("Message envoyé avec succès");
    }
}
