package ml.tamboura.Bibliotheque.repository;

import ml.tamboura.Bibliotheque.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
