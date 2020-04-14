package com.rakeshv.springdatamongodb.config;

import com.rakeshv.springdatamongodb.utils.AircraftDbReaderConverter;
import com.rakeshv.springdatamongodb.utils.AircraftDbWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomerConverterConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new AircraftDbReaderConverter());
        converters.add(new AircraftDbWriteConverter());
        return new MongoCustomConversions(converters);
    }

}
