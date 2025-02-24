package demo.demo.services.payment.dtos;

import java.math.BigInteger;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BankTransferPaymentRequest {
    @NotNull
    @Positive
    private BigInteger amount;

    @NotBlank
    private String currency;

    @NotBlank
    @Email
    private String email; 

    // private String senderAccount;
    // private String receiverAccount;
}
