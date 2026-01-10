package ml.tamboura.Bibliotheque.services;

import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
    }

    public Book create(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public Book update(Long id, Book updatedBook) {
        Book book = findById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
