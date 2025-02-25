package demo.demo.services.payment.dtos;

import lombok.Data;

@Data
public class PaymentResponse<T> {
    private Boolean status;
    private Integer code;
    private String message;
    private T data; 

    public PaymentResponse(Boolean status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public PaymentResponse(Boolean status, String message){
        this.status = status;
        this.message = message;
    }

}


