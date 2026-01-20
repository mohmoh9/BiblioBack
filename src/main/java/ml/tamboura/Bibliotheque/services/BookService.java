package ml.tamboura.Bibliotheque.services;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.BookRequest;
import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByQuantityGreaterThan(0);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
    }

    public Book createBook(BookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .price(request.getPrice())
                .rentable(request.isRentable())
                .sellable(request.isSellable())
                .rentPrice(request.getRentPrice())
                .sellPrice(request.getSellPrice())
                .quantity(request.getQuantity())
                .build();

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookRequest request) {
        Book book = getBookById(id);

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setRentable(request.isRentable());
        book.setSellable(request.isSellable());
        book.setRentPrice(request.getRentPrice());
        book.setSellPrice(request.getSellPrice());
        book.setQuantity(request.getQuantity());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
