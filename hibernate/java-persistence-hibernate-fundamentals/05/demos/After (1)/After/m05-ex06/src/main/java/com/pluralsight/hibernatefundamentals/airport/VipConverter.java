package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.AttributeConverter;

public class VipConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute? "Yes": "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Yes".equalsIgnoreCase(dbData) ?
                Boolean.TRUE:Boolean.FALSE;
    }
}
