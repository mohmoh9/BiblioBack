package ml.tamboura.Bibliotheque.repository;

import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.entity.Loan;
import ml.tamboura.Bibliotheque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser(User user);

    Optional<Loan> findByBookAndReturnDateIsNull(Book book);
}
