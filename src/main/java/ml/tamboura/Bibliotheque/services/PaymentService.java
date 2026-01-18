package ml.tamboura.Bibliotheque.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.dto.PaymentWebhookDTO;
import ml.tamboura.Bibliotheque.entity.Order;
import ml.tamboura.Bibliotheque.entity.OrderStatus;
import ml.tamboura.Bibliotheque.entity.Payment;
import ml.tamboura.Bibliotheque.entity.PaymentStatus;
import ml.tamboura.Bibliotheque.repository.OrderRepository;
import ml.tamboura.Bibliotheque.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public void handleWebhook(PaymentWebhookDTO dto) {

        // 1️⃣ éviter doublons
        if (paymentRepository.existsByTransactionId(dto.getTransactionId())) {
            return;
        }

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 2️⃣ vérifier montant
        if (order.getTotalAmount() != dto.getAmount()) {
            throw new RuntimeException("Invalid payment amount");
        }

        // 3️⃣ enregistrer paiement
        Payment payment = new Payment();
        payment.setProvider(dto.getProvider());
        payment.setTransactionId(dto.getTransactionId());
        payment.setAmount(dto.getAmount());
        payment.setOrder(order);

        if ("SUCCESS".equals(dto.getStatus())) {
            payment.setStatus(PaymentStatus.SUCCESS);
            order.setStatus(OrderStatus.PAID);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }

        paymentRepository.save(payment);
        orderRepository.save(order);
    }
}

