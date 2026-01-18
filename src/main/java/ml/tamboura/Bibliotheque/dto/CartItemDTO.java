package ml.tamboura.Bibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Long bookId;
    private String title;
    private double price;
    private int quantity;
    private double total;
}

