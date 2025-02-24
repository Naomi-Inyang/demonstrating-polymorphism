package demo.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.demo.services.payment.PaymentService;
import demo.demo.services.payment.dtos.PaymentRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<?> chargeUser(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.chargeUser(request));
    }
    
}
