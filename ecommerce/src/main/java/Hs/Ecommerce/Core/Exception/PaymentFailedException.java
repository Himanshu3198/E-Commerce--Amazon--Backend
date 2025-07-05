package Hs.Ecommerce.Core.Exception;

public class PaymentFailedException  extends RuntimeException{

    public PaymentFailedException(String message){
        super(message);
    }
}
