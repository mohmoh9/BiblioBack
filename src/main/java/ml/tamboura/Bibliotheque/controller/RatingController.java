package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.RatingDTO;
import ml.tamboura.Bibliotheque.dto.RatingRequestDTO;
import ml.tamboura.Bibliotheque.entity.User;
import ml.tamboura.Bibliotheque.mapper.RatingMapper;
import ml.tamboura.Bibliotheque.services.RatingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @PostMapping("/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public void rateBook(@AuthenticationPrincipal User user,
                         @PathVariable Long bookId,
                         @RequestBody RatingRequestDTO dto) {
        ratingService.rateBook(user, bookId, dto.getRating());
    }

    @GetMapping("/average/{bookId}")
    public RatingDTO getAverage(@PathVariable Long bookId) {
        double avg = ratingService.getAverage(bookId);
        return ratingMapper.toDTO(bookId, avg);
    }
}
