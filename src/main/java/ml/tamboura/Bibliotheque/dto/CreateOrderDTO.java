package ml.tamboura.Bibliotheque.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private String paymentMethod; // PAYPAL | MOBILE_MONEY
}

