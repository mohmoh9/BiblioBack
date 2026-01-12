package ml.tamboura.Bibliotheque.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequest {
    private String title;
    private String author;
    private String isbn;

    public BookRequest(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public BookRequest() {}

}
