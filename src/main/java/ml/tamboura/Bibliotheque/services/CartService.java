package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.entity.*;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import ml.tamboura.Bibliotheque.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final BookService bookService;
    private final UserService userService;

    public Cart getCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
    }


    public Cart getUserCart() {
        User user = userService.getCurrentUser();
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(null, user, new ArrayList<>())));
    }

    public Cart addToCart(Long bookId, CartActionType type, Integer rentDays) {

        Cart cart = getUserCart();
        Book book = bookService.getBookById(bookId);

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getBook().getId().equals(bookId) && i.getType() == type)
                .findFirst()
                .orElse(null);

        if (item == null) {
            item = new CartItem();
            item.setBook(book);
            item.setQuantity(1);
            item.setType(type);

            if (type == CartActionType.RENT) {
                if (rentDays == null || rentDays <= 0)
                    throw new RuntimeException("Nombre de jours requis");
                item.setRentDays(rentDays);
            }

            cart.getItems().add(item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }

        return cartRepository.save(cart);
    }


    public void checkout() {
        Cart cart = getUserCart();

        for (CartItem item : cart.getItems()) {
            if (item.getType() == CartActionType.BUY) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    bookService.buyBook(item.getBook().getId());
                }
            } else {
                for (int i = 0; i < item.getQuantity(); i++) {
                    bookService.rentBook(item.getBook().getId());
                }
            }
        }

        cart.getItems().clear();
        cartRepository.save(cart);
    }



    /**
     * Supprimer un livre du panier
     */
    public Cart removeBook(User user, Long bookId) {
        Cart cart = getUserCart();

        cart.getItems().removeIf(
                item -> item.getBook().getId().equals(bookId)
        );

        return cartRepository.save(cart);
    }

    public void clearCart(User user) {
        Cart cart = getUserCart();
        cart.getItems().clear();
        cartRepository.save(cart);
    }


    /**
     * Crée un nouveau panier pour l'utilisateur
     */
    private Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());
        return cartRepository.save(cart);
    }
}
