package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.CartItem;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import ml.tamboura.Bibliotheque.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    /**
     * Récupérer le panier de l'utilisateur
     */
    public Cart getCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
    }

    /**
     * Ajouter un livre au panier
     */
    public Cart addBook(User user, Long bookId) {
        Cart cart = getCart(user);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(
                    existingItem.get().getQuantity() + 1
            );
        } else {
            CartItem item = new CartItem();
            item.setBook(book);
            item.setQuantity(1);
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    /**
     * Supprimer un livre du panier
     */
    public Cart removeBook(User user, Long bookId) {
        Cart cart = getCart(user);

        cart.getItems().removeIf(
                item -> item.getBook().getId().equals(bookId)
        );

        return cartRepository.save(cart);
    }

    /**
     * Vider le panier
     */
    public void clearCart(User user) {
        Cart cart = getCart(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}

