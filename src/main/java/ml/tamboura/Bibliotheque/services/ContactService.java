package ml.tamboura.Bibliotheque.services;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.ContactRequest;
import ml.tamboura.Bibliotheque.entity.ContactMessage;
import ml.tamboura.Bibliotheque.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMessageRepository repository;

    public void saveMessage(ContactRequest request) {
        ContactMessage message = ContactMessage.builder()
                .name(request.getName())
                .email(request.getEmail())
                .message(request.getMessage())
                .build();

        repository.save(message);
    }
}
