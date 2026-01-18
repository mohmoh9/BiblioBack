package ml.tamboura.Bibliotheque.dto;

import lombok.Data;

@Data
public class PaymentWebhookDTO {

    private String transactionId;
    private String provider; // PAYPAL | MOBILE_MONEY
    private Long orderId;
    private double amount;
    private String status; // SUCCESS | FAILED
}

