package ml.tamboura.Bibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class BookRequest {

    private String title;
    private String author;
    private String isbn;
    private String description;

    private String coverImage;

    private boolean rentable;
    private boolean sellable;

    private double rentPrice;
    private double sellPrice;

    private int quantity;

    public BookRequest(String title, String author, String isbn, String description, String coverImage, boolean rentable, boolean sellable, double rentPrice, double sellPrice, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.coverImage = coverImage;        this.rentable = rentable;
        this.sellable = sellable;
        this.rentPrice = rentPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public BookRequest() {}

}
