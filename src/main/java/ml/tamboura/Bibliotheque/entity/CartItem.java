package ml.tamboura.Bibliotheque.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Postgres auto-génération
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference
    private Book book;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private CartActionType actionType;

    private int rentDays; // uniquement si RENT
}
