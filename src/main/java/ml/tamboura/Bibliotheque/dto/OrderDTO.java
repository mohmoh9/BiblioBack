package ml.tamboura.Bibliotheque.dto;

import lombok.*;
import ml.tamboura.Bibliotheque.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private List<OrderItemDTO> items;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
}

