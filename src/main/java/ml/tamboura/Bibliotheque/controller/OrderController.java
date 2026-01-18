package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.OrderDTO;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.mapper.OrderMapper;
import ml.tamboura.Bibliotheque.services.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDTO create(@AuthenticationPrincipal User user) {
        return orderMapper.toDTO(
                orderService.createOrder(user)
        );
    }

    @GetMapping
    public List<OrderDTO> myOrders(@AuthenticationPrincipal User user) {
        return orderService.getUserOrders(user)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }
}

