package demo.demo.services.payment.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import demo.demo.services.payment.enums.PaymentMethod;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {
    @NotBlank
    private double amount;

    @NotBlank
    private String currency;

    @NotBlank
    private PaymentMethod paymentMethod; 

    @NotBlank
    @Email
    private String email; 

    @NotBlank
    private String phoneNumber; 
    
    // Card Payment Fields
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;

    // Bank Transfer Fields
    private String senderAccount;
    private String receiverAccount;
    
    // Digital Wallet Fields
    private String walletId;
    
    private String narration; 
}
