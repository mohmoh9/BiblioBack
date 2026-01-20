package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.PaymentWebhookDTO;
import ml.tamboura.Bibliotheque.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class PaymentWebhookController {

    @PostMapping("/payment")
    public ResponseEntity<Void> webhook(
            @RequestHeader(value = "X-Signature", required = false) String signature,
            @RequestBody PaymentWebhookDTO dto
    ) {
        // vérifier signature si présente
        // traiter le paiement
        return ResponseEntity.ok().build();
    }
}


