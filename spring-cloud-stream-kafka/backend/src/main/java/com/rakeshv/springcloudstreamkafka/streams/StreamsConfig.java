package com.rakeshv.springcloudstreamkafka.streams;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({GreetingsStreams.class, SimpleMessageStream.class})
public class StreamsConfig {
}
