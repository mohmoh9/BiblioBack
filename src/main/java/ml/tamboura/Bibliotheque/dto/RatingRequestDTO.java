package ml.tamboura.Bibliotheque.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RatingRequestDTO {
    private int rating; // 1 à 5
}

