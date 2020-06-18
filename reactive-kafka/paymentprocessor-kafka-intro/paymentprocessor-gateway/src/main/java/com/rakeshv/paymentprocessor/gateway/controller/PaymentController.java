package com.rakeshv.paymentprocessor.gateway.controller;

import com.rakeshv.paymentprocessor.gateway.command.CreatePaymentCommand;
import com.rakeshv.paymentprocessor.gateway.gateway.PaymentGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PaymentController {

    private final PaymentGateway paymentGateway;

    public PaymentController(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * The Mono returned by the call will be sent to Spring Webflux, which relies on an multi-reactor event-loop and NIO
     * to handle requests in a non-blocking manner, enabling far more concurrent requests. The result will be sent over
     * HTTP through a mechanism called Server Sent Events
     **/
    @PostMapping(value = "/api/payments/validate/payment")
    public Mono<Void> doPayment(@RequestBody CreatePaymentCommand payment) {
        /**
         When calling the doPayment method, we send our payment information, getting a Mono<Void> in return.
         This event will resolve when our payment is sent succesfully to the Kafka topic
         **/
        return paymentGateway.doPayment(payment);
    }

    @GetMapping("/api/payments/validate/dopayment")
    public Mono<Void> payment() {
        CreatePaymentCommand paymentCommand = new CreatePaymentCommand("abcd", "efgh-ijkl", "100");
        return paymentGateway.doPayment(paymentCommand);
    }
}
