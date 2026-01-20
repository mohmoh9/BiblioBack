package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.CartDTO;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.CartActionType;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.mapper.CartMapper;
import ml.tamboura.Bibliotheque.security.CustomUserDetails;
import ml.tamboura.Bibliotheque.services.CartService;
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

    @GetMapping
    public CartDTO getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        Cart cart = cartService.getCart(user);
        return cartMapper.toDTO(cart);
    }

    @PostMapping("/add/{bookId}")
    public CartDTO addBook(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long bookId,
            @RequestParam CartActionType type,
            @RequestParam(required = false) Integer days
    ) {
        User user = userDetails.getUser();
        Cart cart = cartService.addBook(user, bookId, type, days);
        return cartMapper.toDTO(cart);
    }


    @DeleteMapping("/remove/{bookId}")
    public CartDTO removeBook(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable Long bookId) {
        User user = userDetails.getUser();
        Cart cart = cartService.removeBook(user, bookId);
        return cartMapper.toDTO(cart);
    }

    @DeleteMapping("/clear")
    public void clearCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        cartService.clearCart(user);
    }
}
