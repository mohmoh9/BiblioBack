package ml.tamboura.Bibliotheque.controller;

import lombok.RequiredArgsConstructor;
import ml.tamboura.Bibliotheque.services.PaypalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaypalService paypalService;

    @PostMapping("/paypal")
    public String pay(@RequestParam Double amount) {
        return paypalService.createPayment(amount);
    }

    @PostMapping("/mobile-money")
    public String mobileMoney(@RequestParam Double amount) {
        return "https://paydunya.com/payment";
    }

}

