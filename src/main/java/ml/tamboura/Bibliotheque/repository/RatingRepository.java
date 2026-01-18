package ml.tamboura.Bibliotheque.repository;

import ml.tamboura.Bibliotheque.entity.Book;
import ml.tamboura.Bibliotheque.entity.Rating;
import ml.tamboura.Bibliotheque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("""
        SELECT AVG(r.value)
        FROM Rating r
        WHERE r.book.id = :bookId
    """)
    Double getAverageRating(@Param("bookId") Long bookId);

    Optional<Rating> findByUserAndBook(User user, Book book);

}
