package ml.tamboura.Bibliotheque.repository;

import ml.tamboura.Bibliotheque.entity.Order;
import ml.tamboura.Bibliotheque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByCreatedAtDesc(User user);
}

