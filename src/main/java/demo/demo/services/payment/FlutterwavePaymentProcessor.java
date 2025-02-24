package demo.demo.services.payment;

import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import demo.demo.services.payment.contracts.PaymentProcessor;
import demo.demo.services.payment.dtos.PaymentRequest;
import demo.demo.services.payment.dtos.PaymentResponse;
import demo.demo.services.payment.mappers.PaymentRequestMapper;

import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Primary
public class FlutterwavePaymentProcessor implements PaymentProcessor {
    private static final String BASE_URL = "https://api.flutterwave.com/v3";
    private static final String secretKey = "FLWSECK_TEST-83d906ae50ce91462500a5881ee30abd-X";
    private static final String encryptionKey = "FLWSECK_TEST8851f5a9619";

    private final WebClient webClient;

    @Override
    public PaymentResponse<?> initializePayment(PaymentRequest request) {
        return initializeCardPayment(request);
    }

    @Override
    public PaymentResponse<?> verifyPayment(String transactionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyPayment'");
    }

    private PaymentResponse<?> initializeCardPayment(PaymentRequest request){
        Map<String, Object> requestBody = PaymentRequestMapper.toFlutterwaveFormat(request);

        Mono<String> clientResponse = webClient.post()
                 .uri(BASE_URL+"/charges?type=card")
                 .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                 .bodyValue(requestBody)
                 .retrieve()
                 .bodyToMono(String.class);

        System.out.println(clientResponse);

        return new PaymentResponse<>(true, "stuff", clientResponse);
    }
}
