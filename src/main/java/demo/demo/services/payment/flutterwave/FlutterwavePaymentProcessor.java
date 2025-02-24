package demo.demo.services.payment.flutterwave;

import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import demo.demo.services.payment.contracts.PaymentProcessor;
import demo.demo.services.payment.dtos.BankTransferPaymentRequest;
import demo.demo.services.payment.dtos.CardPaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;
import demo.demo.services.payment.enums.PaymentMethod;
import demo.demo.services.payment.mappers.PaymentRequestMapper;

import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


import org.json.JSONObject;

@RequiredArgsConstructor
@Component
@Primary
public class FlutterwavePaymentProcessor implements PaymentProcessor {
    @Value("${FLUTTERWAVE_SECRET_KEY}")
    private static String secretKey;
    
    @Value("${FLUTTERWAVE_ENCRYPTION_KEY}")
    private static String encryptionKey;

    private static final String BASE_URL = "https://api.flutterwave.com/v3";
    private static final String ENCRYPTION_ALGORITHM = "DESede";
    private static final String ENCRYPTION_TRANSFORMATION = "DESede/ECB/PKCS5Padding";

    private final WebClient webClient;

    @Override
    public PaymentResponse<?> initializeBankTransferPayment(BankTransferPaymentRequest request) {
        System.out.println(encryptionKey);

        Map<String, Object> requestBody = PaymentRequestMapper.mapToFlutterwaveBankTransferFormat(request);

        RequestBodySpec requestBodySpec = generateRequestBodySpecification(PaymentMethod.BANK_TRANSFER.name().toLowerCase());

        Mono<String> clientResponse = requestBodySpec.bodyValue(requestBody) 
                                                     .retrieve()
                                                     .bodyToMono(String.class);

        String response = clientResponse.block();

        return new PaymentResponse<>(true, "Bank transfer payment initialized", response);
    }


    @Override
    public PaymentResponse<?> initializeCardPayment(CardPaymentRequest request){
        Map<String, Object> requestBody = PaymentRequestMapper.mapToFlutterwaveCardChargeFormat(request);

        String stringifiedRequestBody= new JSONObject(requestBody).toString();  

        String encryptedRequestBody = encryptRequestBody(stringifiedRequestBody, encryptionKey);

        RequestBodySpec requestBodySpec = generateRequestBodySpecification(PaymentMethod.CARD.name().toLowerCase());

        Mono<String> clientResponse = requestBodySpec.bodyValue(new JSONObject().put("client", encryptedRequestBody).toString()) 
                                                     .retrieve()
                                                     .bodyToMono(String.class);

        String response = clientResponse.block();

        return new PaymentResponse<>(true, "Card payment initialized", response);
    }

    @Override
    public PaymentResponse<?> verifyPayment(String transactionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyPayment'");
    }

    private RequestBodySpec generateRequestBodySpecification(String channel) {
        RequestBodySpec requestSpec = webClient.post()
                                               .uri(BASE_URL + "/charges?type=" + channel)
                                               .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                                               .header(HttpHeaders.CONTENT_TYPE, "application/json");

        return requestSpec;
    }


    private String encryptRequestBody(String data, String encryptionKey) {
        try {
            final byte[] encryptionKeyBytes = Arrays.copyOf(encryptionKey.getBytes(StandardCharsets.UTF_8), 24);
            final SecretKeySpec secretKey = new SecretKeySpec(encryptionKeyBytes, ENCRYPTION_ALGORITHM);

            final Cipher cipher = Cipher.getInstance(ENCRYPTION_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            final byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedValue = cipher.doFinal(dataBytes);

            return Base64.getEncoder().encodeToString(encryptedValue);

        } catch (Exception exception) {
            System.out.println("Encryption Error:");
            exception.printStackTrace(); 
            return null;
        }
    }
}
