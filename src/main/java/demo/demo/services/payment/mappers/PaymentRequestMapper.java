package demo.demo.services.payment.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import demo.demo.services.payment.dtos.BankTransferPaymentRequest;
import demo.demo.services.payment.dtos.CardPaymentRequest;

public class PaymentRequestMapper {

    public static Map<String, Object> mapToFlutterwaveBankTransferFormat(BankTransferPaymentRequest request) {
        Map<String, Object> mappedRequest = new HashMap<>();

        mappedRequest.put("amount", request.getAmount()); 
        mappedRequest.put("currency", request.getCurrency());
        mappedRequest.put("email", request.getEmail());
        mappedRequest.put("tx_ref", "TXN-" + generateTransactionReference());

        return mappedRequest;
    }

    public static Map<String, Object> mapToFlutterwaveCardChargeFormat(CardPaymentRequest request) {
        Map<String, Object> mappedRequest = new HashMap<>();

        mappedRequest.put("amount", request.getAmount()); 
        mappedRequest.put("currency", request.getCurrency());
        mappedRequest.put("email", request.getEmail());
        mappedRequest.put("tx_ref", "TXN-" + generateTransactionReference());
        mappedRequest.put("card_number", request.getCardNumber());
        mappedRequest.put("cvv", request.getCvv());
        mappedRequest.put("expiry_month", Integer.parseInt(request.getExpiryMonth())); // Convert to int
        mappedRequest.put("expiry_year", Integer.parseInt(request.getExpiryYear())); // Convert to int

        return mappedRequest;
    }

    private static String generateTransactionReference(){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String transactionRef = timestamp + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        return transactionRef;
    }
}
