package ml.tamboura.Bibliotheque.dto;

import lombok.Getter;
import lombok.Setter;
import ml.tamboura.Bibliotheque.entity.CartActionType;

@Getter
@Setter
public class AddToCartRequest {
    private Long bookId;
    private CartActionType type; // BUY / RENT
}

