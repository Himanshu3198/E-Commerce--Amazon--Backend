package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Enum.PaymentMethod;

public interface ICheckoutService {

    void checkout(Long orderId, PaymentMethod paymentMethod);
}
