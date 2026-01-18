package ml.tamboura.Bibliotheque.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long bookId;
    private String title;
    private int quantity;
    private double price;
    private double total;
}
