package ml.tamboura.Bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderItems")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    private Book book;

    private int quantity;
    private double price;
}

