package Hs.Ecommerce.Core.Exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String response){
        super(response);
    }
}
