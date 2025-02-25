package demo.demo.services.payment.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardPaymentRequest {
    @NotNull
    @Positive
    private BigInteger amount;

    @NotBlank
    private String currency;

    @NotBlank
    @Email
    private String email; 

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cvv;

    @NotBlank
    private String expiryMonth;

    @NotBlank
    private String expiryYear;

    // // Bank Transfer Fields
    // private String senderAccount;
    // private String receiverAccount;
    
    // // Digital Wallet Fields
    // private String walletId;
    
    // private String narration; 
}
