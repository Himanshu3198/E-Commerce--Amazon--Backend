package Hs.Ecommerce.Core.Exception;

public class OrderFailedException extends RuntimeException {
    public OrderFailedException(String message) {
        super(message);
    }
}
