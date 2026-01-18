package ml.tamboura.Bibliotheque.mapper;

import ml.tamboura.Bibliotheque.dto.CartDTO;
import ml.tamboura.Bibliotheque.dto.CartItemDTO;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart) {

        List<CartItemDTO> items = cart.getItems().stream()
                .map(this::toItemDTO)
                .toList();

        double total = items.stream()
                .mapToDouble(CartItemDTO::getTotal)
                .sum();

        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getId());
        dto.setItems(items);
        dto.setTotalPrice(total);

        return dto;
    }

    private CartItemDTO toItemDTO(CartItem item) {

        CartItemDTO dto = new CartItemDTO();
        dto.setBookId(item.getBook().getId());
        dto.setTitle(item.getBook().getTitle());
        dto.setPrice(item.getBook().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setTotal(item.getBook().getPrice() * item.getQuantity());

        return dto;
    }
}

