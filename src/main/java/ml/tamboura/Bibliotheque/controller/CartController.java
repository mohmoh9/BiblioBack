package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.CartDTO;
import ml.tamboura.Bibliotheque.entity.Cart;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.mapper.CartMapper;
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
    public CartDTO getCart(@AuthenticationPrincipal User user) {
        return cartMapper.toDTO(
                cartService.getCart(user)
        );
    }

    @PostMapping("/add/{bookId}")
    public CartDTO add(@AuthenticationPrincipal User user,
                       @PathVariable Long bookId) {
        return cartMapper.toDTO(
                cartService.addBook(user, bookId)
        );
    }

    @DeleteMapping("/remove/{bookId}")
    public CartDTO remove(@AuthenticationPrincipal User user,
                          @PathVariable Long bookId) {
        return cartMapper.toDTO(
                cartService.removeBook(user, bookId)
        );
    }
}

