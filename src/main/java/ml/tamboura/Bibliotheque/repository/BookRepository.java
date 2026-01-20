package ml.tamboura.Bibliotheque.repository;

import ml.tamboura.Bibliotheque.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByQuantityGreaterThan(int quantity);
}


