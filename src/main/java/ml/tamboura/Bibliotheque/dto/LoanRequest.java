package ml.tamboura.Bibliotheque.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoanRequest {
    private Long userId;
    private Long bookId;

    // Constructeurs
    public LoanRequest() {}

    public LoanRequest(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

}
