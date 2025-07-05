package Hs.Ecommerce.Core.Payment;


import Hs.Ecommerce.Core.Enum.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

   public static PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod){
       return switch (paymentMethod){
           case CARD -> new CardPaymentImp();
           case UPI -> new UpiPaymentImp();
           default -> throw new IllegalArgumentException("Unsupported payment method: "+paymentMethod);
       };
   }
}
