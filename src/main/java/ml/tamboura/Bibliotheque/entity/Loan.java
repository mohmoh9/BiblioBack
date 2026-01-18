package ml.tamboura.Bibliotheque.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate loanDate;      // date d’emprunt
    private LocalDate dueDate;       // date de retour prévue
    private LocalDate returnDate;    // date retour réelle

    private int durationDays;        // 👈 durée en jours
    private double price;            // prix total location
}
