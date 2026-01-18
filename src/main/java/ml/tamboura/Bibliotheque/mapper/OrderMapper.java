package ml.tamboura.Bibliotheque.mapper;

import ml.tamboura.Bibliotheque.dto.OrderDTO;
import ml.tamboura.Bibliotheque.dto.OrderItemDTO;
import ml.tamboura.Bibliotheque.entity.Order;
import ml.tamboura.Bibliotheque.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {

        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemDTO> items = order.getItems()
                .stream()
                .map(this::toItemDTO)
                .toList();

        dto.setItems(items);

        return dto;
    }

    private OrderItemDTO toItemDTO(OrderItem item) {

        OrderItemDTO dto = new OrderItemDTO();
        dto.setBookId(item.getBook().getId());
        dto.setTitle(item.getBook().getTitle());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setTotal(item.getPrice() * item.getQuantity());

        return dto;
    }
}

