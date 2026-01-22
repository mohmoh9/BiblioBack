package ml.tamboura.Bibliotheque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

