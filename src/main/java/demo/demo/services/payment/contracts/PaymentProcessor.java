package demo.demo.services.payment.contracts;

import demo.demo.services.payment.dtos.BankTransferPaymentRequest;
import demo.demo.services.payment.dtos.CardPaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;

public interface PaymentProcessor {
    PaymentResponse<?> initializeCardPayment(CardPaymentRequest request);
    PaymentResponse<?> initializeBankTransferPayment(BankTransferPaymentRequest request);
    PaymentResponse<?> verifyPayment(String transactionId);
}
