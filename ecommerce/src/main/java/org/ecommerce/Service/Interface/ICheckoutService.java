package org.ecommerce.Service.Interface;

import org.ecommerce.Enum.PaymentMethod;

public interface ICheckoutService {

    void checkout(Long orderId, PaymentMethod paymentMethod);
}
