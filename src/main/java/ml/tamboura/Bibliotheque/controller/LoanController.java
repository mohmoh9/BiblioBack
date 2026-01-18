package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.LoanRequest;
import ml.tamboura.Bibliotheque.entity.Loan;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/rent")
    public Loan rentBook(
            @RequestBody LoanRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return loanService.borrowBook(user.getId(), request);
    }

    @PutMapping("/return/{id}")
    public Loan returnBook(@PathVariable Long id) {
        return loanService.returnBook(id);
    }
}

