package ml.tamboura.Bibliotheque.dto;

public class LoanRequest {
    private Long userId;
    private Long bookId;

    // Constructeurs
    public LoanRequest() {}

    public LoanRequest(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
