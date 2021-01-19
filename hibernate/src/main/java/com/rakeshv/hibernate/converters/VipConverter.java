package com.rakeshv.hibernate.converters;

import javax.persistence.AttributeConverter;

public class VipConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "YES" : "NO";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "YES".equalsIgnoreCase(dbData) ? Boolean.TRUE : Boolean.FALSE;
    }
}
