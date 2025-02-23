package demo.demo.services.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class PaymentResponse {
    private boolean status; 
    private String message; 
    private String transactionId; 
    private double amount; 
}
