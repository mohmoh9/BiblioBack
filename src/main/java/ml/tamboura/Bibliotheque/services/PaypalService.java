package ml.tamboura.Bibliotheque.services;

import org.springframework.stereotype.Service;

@Service
public class PaypalService {

    public String createPayment(Double amount) {
        // SDK PayPal
        return "https://paypal.com/checkout";
    }
}

