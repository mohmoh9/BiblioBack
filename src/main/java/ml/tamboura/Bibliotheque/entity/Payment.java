package ml.tamboura.Bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // PAYPAL | MOBILE_MONEY
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private double amount;

    @ManyToOne
    private Order order;

    private LocalDateTime createdAt = LocalDateTime.now();
}

