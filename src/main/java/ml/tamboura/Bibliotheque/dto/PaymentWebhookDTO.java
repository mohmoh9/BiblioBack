package ml.tamboura.Bibliotheque.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentWebhookDTO {

    private String transactionId;
    private String provider; // PAYPAL | MOBILE_MONEY
    private Long orderId;
    private double amount;
    private String status; // SUCCESS | FAILED
}

