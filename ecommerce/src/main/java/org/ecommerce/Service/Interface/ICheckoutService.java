package org.ecommerce.Service.Interface;

import org.ecommerce.Enum.PaymentMethod;
import org.ecommerce.Payment.PaymentStrategy;
import org.ecommerce.Service.Implementation.OrderServiceImp;

public interface ICheckoutService {

    void checkout(Long orderId, PaymentMethod paymentMethod);
}
