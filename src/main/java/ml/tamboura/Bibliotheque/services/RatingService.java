package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.entity.Rating;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.repository.BookRepository;
import ml.tamboura.Bibliotheque.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    public void rateBook(User user, Long bookId, int value) {

        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Rating rating = ratingRepository
                .findByUserAndBook(user, book)
                .orElse(new Rating());

        rating.setUser(user);
        rating.setBook(book);
        rating.setValue(value);

        ratingRepository.save(rating);
    }

    public double getAverage(Long bookId) {
        return ratingRepository.getAverageRating(bookId) != null
                ? ratingRepository.getAverageRating(bookId)
                : 0.0;
    }
}
