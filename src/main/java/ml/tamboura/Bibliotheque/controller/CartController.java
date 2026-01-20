package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.CartDTO;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.CartActionType;
import ml.tamboura.Bibliotheque.mapper.CartMapper;
import ml.tamboura.Bibliotheque.security.CustomUserDetails;
import ml.tamboura.Bibliotheque.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    // 🔹 Récupérer le panier
    @GetMapping
    public ResponseEntity<CartDTO> getCart() {
        Cart cart = cartService.getUserCart();
        return ResponseEntity.ok(cartMapper.toDTO(cart));
    }

    // 🔹 Ajouter un livre (BUY ou RENT)
    @PostMapping("/add/{bookId}")
    public ResponseEntity<CartDTO> addToCart(
            @PathVariable Long bookId,
            @RequestParam CartActionType type,
            @RequestParam(required = false) Integer days
    ) {

        // ✅ Validation métier
        if (type == CartActionType.RENT && (days == null || days <= 0)) {
            return ResponseEntity.badRequest().build();
        }

        Cart cart = cartService.addToCart(bookId, type, days);
        return ResponseEntity.ok(cartMapper.toDTO(cart));
    }

    // 🔹 Supprimer un livre
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<CartDTO> removeBook(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long bookId
    ) {
        Cart cart = cartService.removeBook(userDetails.getUser(), bookId);
        return ResponseEntity.ok(cartMapper.toDTO(cart));
    }

    // 🔹 Vider le panier
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cartService.clearCart(userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    // 🔹 Checkout
    @PostMapping("/checkout")
    public ResponseEntity<Void> checkout() {
        cartService.checkout();
        return ResponseEntity.ok().build();
    }
}
