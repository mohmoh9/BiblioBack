package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.LoanRequest;
import ml.tamboura.Bibliotheque.entity.Loan;
import ml.tamboura.Bibliotheque.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping

    public ResponseEntity<Loan> borrowBook(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.borrowBook(request.getBookId()));
    }



    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }
}
