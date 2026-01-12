package ml.tamboura.Bibliotheque.services;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.LoanRequest;
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
@RequiredArgsConstructor
public class LoanService {

    private final UserService  userService;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    // 📌 Emprunter un livre
    public Loan borrowBook(Long bookId) {

        User user = userService.getCurrentUser();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Livre déjà emprunté");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDate.now())
                .build();

        return loanRepository.save(loan);
    }

    // 📌 Retourner un livre
    public Loan returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    // 📌 Tous les emprunts
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // 📌 Emprunts d’un utilisateur
    public List<Loan> getLoansByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return loanRepository.findByUser(user);
    }
}
