package org.ecommerce.Exception;

public class PaymentFailedException  extends RuntimeException{

    public PaymentFailedException(String message){
        super(message);
    }
}
