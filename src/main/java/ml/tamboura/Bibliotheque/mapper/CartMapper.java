package ml.tamboura.Bibliotheque.mapper;

import ml.tamboura.Bibliotheque.dto.BookDTO;
import ml.tamboura.Bibliotheque.dto.CartDTO;
import ml.tamboura.Bibliotheque.dto.CartItemDTO;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        return CartDTO.builder()
                .items(cart.getItems().stream()
                        .map(this::toItemDTO)
                        .collect(Collectors.toList()))
                .total(0) // Le total sera calculé côté service
                .build();
    }

    public CartItemDTO toItemDTO(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .book(toBookDTO(item.getBook()))
                .build();
    }

    public BookDTO toBookDTO(ml.tamboura.Bibliotheque.entity.Book book) {
        if (book == null) return null;
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }
}
