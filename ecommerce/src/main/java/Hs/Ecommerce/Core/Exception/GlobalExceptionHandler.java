package Hs.Ecommerce.Core.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(OrderFailedException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderFailedException ex){
        ErrorResponse errorResponse = new ErrorResponse("ORDER_FAILED", ex.getMessage(),"check payment status");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ErrorResponse> handlePaymentFailed(PaymentFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "PAYMENT_FAILED",                            // errorCode
                ex.getMessage(),                             // message from exception
                "Please check your payment details or try another method" // help message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "PRODUCT_NOT_FOUND",                            // errorCode
                ex.getMessage(),                             // message from exception
                "this is product is not available try other product" // help message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "USER_NOT_FOUND",                            // errorCode
                ex.getMessage(),                             // message from exception
                "try register/login" // help message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCartException.class)
    public ResponseEntity<ErrorResponse> handleUserCartException(UserCartException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "USER_NOT_FOUND",                   // Custom error code
                ex.getMessage(),                    // Exception message
                "Please try registering or logging in to access your cart"  // Help message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(
          "RESOURCE_NOT_FOUND",
          ex.getMessage(),
          "Check if Resource exists in DB or not"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(OrderFailedException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                "ORDER_NOT_FOUND",
                ex.getMessage(),
                "check orderId already exist or not"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


}
