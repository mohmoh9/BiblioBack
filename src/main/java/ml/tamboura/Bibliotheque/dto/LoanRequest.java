package ml.tamboura.Bibliotheque.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoanRequest {

        private Long bookId;
        private int durationDays; // 7, 14, 30 jours


    // Constructeurs
    public LoanRequest() {}

    public LoanRequest(Long bookId, int durationDays) {
        this.bookId = bookId;
        this.durationDays = durationDays;
    }
}
