package ml.tamboura.Bibliotheque.coltroller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.LoanRequest;
import ml.tamboura.Bibliotheque.entity.Loan;
import ml.tamboura.Bibliotheque.services.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

        private final LoanService loanService;

        @PostMapping
        public Loan borrowBook(@RequestBody LoanRequest request) {
            return loanService.borrowBook(request);
        }

        @PutMapping("/{id}/return")
        public Loan returnBook(@PathVariable Long id) {
            return loanService.returnBook(id);
        }

        @GetMapping
        public List<Loan> getAllLoans() {
            return loanService.getAllLoans();
        }

        @GetMapping("/user/{userId}")
        public List<Loan> getLoansByUser(@PathVariable Long userId) {
            return loanService.getLoansByUser(userId);
        }
}
