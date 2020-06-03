package com.rakeshv.paymentprocessor.gateway.gateway;

import com.rakeshv.paymentprocessor.gateway.command.CreatePaymentCommand;
import reactor.core.publisher.Mono;

public interface PaymentGateway {

    Mono<Void> doPayment(CreatePaymentCommand createPayment);

}
