package com.rakeshv.springdatamongodb.utils;

import com.rakeshv.springdatamongodb.domains.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbReaderConverter implements Converter<String, Aircraft> {
    @Override
    public Aircraft convert(String s) {
        if (s == null) {
            return null;
        }

        String[] aircraft = s.split("/");

        return Aircraft.builder()
                .model(aircraft[0])
                .nbSeats(Integer.parseInt(aircraft[1]))
                .build();
    }
}
