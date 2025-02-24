package demo.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.demo.services.payment.PaymentService;
import demo.demo.services.payment.dtos.BankTransferPaymentRequest;
import demo.demo.services.payment.dtos.CardPaymentRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/card")
    public ResponseEntity<?> initiateCardTransfer(@Valid @RequestBody CardPaymentRequest request) {
        return ResponseEntity.ok(paymentService.initiateCardPayment(request));
    }

    @PostMapping("/bank_transfer")
    public ResponseEntity<?> initiateBankTransfer(@Valid @RequestBody BankTransferPaymentRequest request) {
        return ResponseEntity.ok(paymentService.initiateBankTransferPayment(request));
    }
    
}
