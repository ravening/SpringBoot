package com.rakeshv.springcloudstreamkafka.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface SimpleMessageStream {
    String INPUT = "messages-in";
    String OUPUT = "messages-out";

    @Input(INPUT)
    SubscribableChannel inboundMessage();

    @Output(OUPUT)
    MessageChannel outboundMessage();
}
