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
    private final BookRepository bookRepository;

    /**
     * Récupérer le panier de l'utilisateur, créer s'il n'existe pas
     */
    public Cart getCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
    }

    /**
     * Ajouter un livre au panier
     */
    public Cart addBook(User user, Long bookId, CartActionType type, Integer days) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        if (type == CartActionType.RENT && !book.isRentable())
            throw new RuntimeException("Livre non louable");

        if (type == CartActionType.BUY && !book.isSellable())
            throw new RuntimeException("Livre non vendable");

        Cart cart = getCart(user);

        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(1);
        item.setActionType(type);

        if (type == CartActionType.RENT) {
            if (days == null || days <= 0)
                throw new RuntimeException("Durée de location invalide");
            item.setRentDays(days);
        }

        cart.getItems().add(item);
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
