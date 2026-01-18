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
public class LoanService {

    private final LoanRepository loanRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public LoanService(
            LoanRepository loanRepo,
            BookRepository bookRepo,
            UserRepository userRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public Loan borrowBook(Long userId, LoanRequest request) {

        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        if (!book.isRentable()) {
            throw new RuntimeException("Livre non louable");
        }

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Stock épuisé");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(request.getDurationDays());

        double totalPrice = book.getRentPrice() * request.getDurationDays();

        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(loanDate)
                .dueDate(dueDate)
                .durationDays(request.getDurationDays())
                .price(totalPrice)
                .build();

        return loanRepo.save(loan);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable"));

        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);

        return loanRepo.save(loan);
    }
}
