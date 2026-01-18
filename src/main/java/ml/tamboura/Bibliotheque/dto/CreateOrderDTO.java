package ml.tamboura.Bibliotheque.dto;

import lombok.Data;

@Data
public class CreateOrderDTO {
    private String paymentMethod; // PAYPAL | MOBILE_MONEY
}

