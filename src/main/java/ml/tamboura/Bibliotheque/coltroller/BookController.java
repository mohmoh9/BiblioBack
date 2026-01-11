package ml.tamboura.Bibliotheque.coltroller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.BookRequest;
import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

        private final BookService bookService;

        @PostMapping
        public Book createBook(@RequestBody BookRequest request) {
            return bookService.createBook(request);
        }

        @GetMapping
        public List<Book> getAllBooks() {
            return bookService.getAllBooks();
        }

        @GetMapping("/available")
        public List<Book> getAvailableBooks() {
            return bookService.getAvailableBooks();
        }

        @GetMapping("/{id}")
        public Book getBookById(@PathVariable Long id) {
            return bookService.getBookById(id);
        }

        @PutMapping("/{id}")
        public Book updateBook(
                @PathVariable Long id,
                @RequestBody BookRequest request) {

            return bookService.updateBook(id, request);
        }

        @DeleteMapping("/{id}")
        public void deleteBook(@PathVariable Long id) {
            bookService.deleteBook(id);
        }
}
