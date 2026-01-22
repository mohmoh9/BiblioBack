package ml.tamboura.Bibliotheque.dto;

import lombok.*;
import ml.tamboura.Bibliotheque.entity.CartActionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartRequest {
    private Long bookId;
    private CartActionType type; // BUY / RENT
}

