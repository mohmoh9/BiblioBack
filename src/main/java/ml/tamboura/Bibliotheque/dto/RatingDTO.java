package ml.tamboura.Bibliotheque.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private Long bookId;
    private double average;
}

