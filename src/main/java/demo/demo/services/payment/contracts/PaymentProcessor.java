package demo.demo.services.payment.contracts;

import demo.demo.services.payment.dtos.PaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;

public interface PaymentProcessor {
    PaymentResponse<?> initializePayment(PaymentRequest request);
    PaymentResponse<?> verifyPayment(String transactionId);
}
