package ml.tamboura.Bibliotheque.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private BookDTO book;
    private int quantity;
}
