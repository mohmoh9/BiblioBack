package ml.tamboura.Bibliotheque.services;

import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.entity.Loan;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import ml.tamboura.Bibliotheque.repository.LoanRepository;
import ml.tamboura.Bibliotheque.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(UserRepository userRepository, LoanRepository loanRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    // 📌 Emprunter un livre
    public Loan borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Livre déjà emprunté");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan(user, book);
        return loanRepository.save(loan);
    }

    // 📌 Retourner un livre
    public void returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        loanRepository.save(loan);
    }

    // 📌 Voir les emprunts d’un utilisateur
    public List<Loan> getLoansByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return loanRepository.findByUser(user);
    }
}
