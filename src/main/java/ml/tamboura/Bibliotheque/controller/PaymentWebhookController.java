package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.PaymentWebhookDTO;
import ml.tamboura.Bibliotheque.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> webhook(
            @RequestBody PaymentWebhookDTO dto) {

        paymentService.handleWebhook(dto);
        return ResponseEntity.ok("Webhook processed");
    }

    @PostMapping
    public ResponseEntity<String> webhook(
            @RequestHeader("X-WEBHOOK-SECRET") String secret,
            @RequestBody PaymentWebhookDTO dto) {

        if (!secret.equals("MY_SECRET_KEY")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        paymentService.handleWebhook(dto);
        return ResponseEntity.ok("OK");
    }

}

