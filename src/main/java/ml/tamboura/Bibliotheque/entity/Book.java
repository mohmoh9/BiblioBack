package ml.tamboura.Bibliotheque.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150)
    private String title;

    @Column(nullable = false,length = 100)
    private String author;

    @Column(nullable = false,length = 50)
    private String isbn;

    @Column(nullable = false,length = 200)
    private String description;

    @Column(nullable = false)
    private double price;

    private boolean rentable; // louable
    private boolean sellable; // vendable

    private double rentPrice; // prix de location
    private double sellPrice; // prix de vente

    private int quantity; // stock disponible

    public Book(Long bookId) {
    }

}



