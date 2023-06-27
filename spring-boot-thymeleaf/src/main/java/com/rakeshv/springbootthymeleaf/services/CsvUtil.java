package com.degiro.csdr.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CsvUtils {

    private static CsvMapper csvMapper;

    public static <T> ObjectReader getReader(Class<T> clazz, boolean withHeader, @NotNull Character separator) {
        ObjectReader reader = getReader(clazz);
        CsvSchema schema = createSchema(clazz, withHeader, separator, null);

        return reader.with(schema);
    }

    public static <T> ObjectWriter getWriter(Class<T> clazz, boolean withHeader, @NotNull Character separator) {
        return getWriter(clazz, withHeader, separator, null);
    }

    public static <T> ObjectWriter getWriter(Class<T> clazz, boolean withHeader, @NotNull Character separator,
            @Nullable String lineSeparator) {

        ObjectWriter writer = getWriter(clazz);
        CsvSchema schema = createSchema(clazz, withHeader, separator, lineSeparator);

        return writer.with(schema);
    }

    private static <T> ObjectReader getReader(Class<T> clazz) {
        return getOrCreateMapper().readerFor(clazz);
    }

    private static <T> ObjectWriter getWriter(Class<T> clazz) {
        return getOrCreateMapper().writerFor(clazz);
    }

    private static <T> CsvSchema createSchema(Class<T> clazz, boolean withHeader, @NotNull Character separator,
        @Nullable String lineSeparator) {
        var schema = csvMapper.schemaFor(clazz)
                              .withoutEscapeChar()
                              .withoutComments()
                              .withoutQuoteChar()
                              .withColumnSeparator(separator);

        if (withHeader) {
            schema = schema.withHeader();
        }

        if (lineSeparator != null) {
            schema = schema.withLineSeparator(lineSeparator);
        }

        return schema;
    }

    private static CsvMapper getOrCreateMapper() {
        if (csvMapper == null) {
            csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());
            csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        }
        return csvMapper;
    }
}
