package com.rakeshv.paymentprocessor.paymentvalidator.validator;

import com.rakeshv.paymentprocessor.common.event.PaymentEvent;

public interface PaymentValidator {

    void calculateResult(PaymentEvent paymentEvent);

}
