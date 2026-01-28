package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
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
                .rentable(request.isRentable())
                .sellable(request.isSellable())
                .rentPrice(request.getRentPrice())
                .sellPrice(request.getSellPrice())
                .quantity(request.getQuantity())
                .coverImage(request.getCoverImage())
                .build();

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookRequest request) {
        Book book = getBookById(id);

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setRentable(request.isRentable());
        book.setSellable(request.isSellable());
        book.setRentPrice(request.getRentPrice());
        book.setSellPrice(request.getSellPrice());
        book.setQuantity(request.getQuantity());
        book.setCoverImage(request.getCoverImage());

        return bookRepository.save(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }


    /* ================= BUY ================= */

    @Transactional
    public Book buyBook(Long bookId) {
        Book book = getBookById(bookId);

        if (!book.isSellable())
            throw new RuntimeException("Livre non vendable");

        validateStock(book);

        book.setQuantity(book.getQuantity() - 1);
        return bookRepository.save(book);
    }

    /* ================= RENT ================= */

    @Transactional
    public Book rentBook(Long bookId) {
        Book book = getBookById(bookId);

        if (!book.isRentable())
            throw new RuntimeException("Livre non louable");

        validateStock(book);

        book.setQuantity(book.getQuantity() - 1);
        return bookRepository.save(book);
    }

    /* ================= RETURN ================= */

    @Transactional
    public Book returnBook(Long id) {
        Book book = getBookById(id);
        book.setQuantity(book.getQuantity() + 1);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private void validateStock(Book book) {
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Stock insuffisant");
        }
    }
}
