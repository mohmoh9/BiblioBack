package ml.tamboura.Bibliotheque.dto;

import lombok.Data;
import ml.tamboura.Bibliotheque.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long orderId;
    private List<OrderItemDTO> items;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
}

