package demo.demo.services.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaymentResponse<T> {
    private Boolean status;
    private Integer code;
    private String message;
    private T data; 

    public PaymentResponse(Boolean status, int code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public PaymentResponse(Boolean status, String message, T data){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public PaymentResponse(Boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public PaymentResponse(){

    }
}


