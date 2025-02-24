package demo.demo.services.payment;

import org.springframework.stereotype.Service;

import demo.demo.services.payment.contracts.PaymentProcessor;
import demo.demo.services.payment.dtos.PaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    public PaymentResponse<?> chargeUser(PaymentRequest request) {
        return paymentProcessor.initializePayment(request);
    }
}
