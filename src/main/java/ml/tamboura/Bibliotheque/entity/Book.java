package ml.tamboura.Bibliotheque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;

    @Column(length = 500)
    private String description;

    private boolean rentable;
    private boolean sellable;

    private String coverImage;

    private double rentPrice;
    private double sellPrice;

    private int quantity;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();
}
