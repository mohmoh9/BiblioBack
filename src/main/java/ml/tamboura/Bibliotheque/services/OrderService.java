package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.entity.*;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import ml.tamboura.Bibliotheque.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final BookRepository bookRepository;

    public Order createOrder(User user) {

        Cart cart = cartService.getCart(user);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        double total = 0;

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setBook(cartItem.getBook());
            item.setQuantity(cartItem.getQuantity());
            item.setSellprice(cartItem.getBook().getSellPrice());

            total += item.getSellprice() * item.getQuantity();

            order.getItems().add(item);
        }

        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);

        cartService.clearCart(user);

        return saved;
    }

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(user);
    }
}

