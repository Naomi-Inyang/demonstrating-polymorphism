package demo.demo.services.payment;

import org.springframework.stereotype.Service;

import demo.demo.services.payment.contracts.PaymentProcessor;
import demo.demo.services.payment.dtos.BankTransferPaymentRequest;
import demo.demo.services.payment.dtos.CardPaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    public PaymentResponse<?> initiateCardPayment(CardPaymentRequest request) {
        return paymentProcessor.initializeCardPayment(request);
    }

    public PaymentResponse<?> initiateBankTransferPayment(BankTransferPaymentRequest request) {
        return paymentProcessor.initializeBankTransferPayment(request);
    }
}
