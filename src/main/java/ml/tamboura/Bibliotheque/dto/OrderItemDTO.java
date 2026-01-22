package ml.tamboura.Bibliotheque.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    private Long bookId;
    private String title;
    private int quantity;
    private double price;
    private double total;
}
