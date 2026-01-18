package ml.tamboura.Bibliotheque.mapper;

import ml.tamboura.Bibliotheque.dto.RatingDTO;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingDTO toDTO(Long bookId, double average) {
        RatingDTO dto = new RatingDTO();
        dto.setBookId(bookId);
        dto.setAverage(average);
        return dto;
    }
}

