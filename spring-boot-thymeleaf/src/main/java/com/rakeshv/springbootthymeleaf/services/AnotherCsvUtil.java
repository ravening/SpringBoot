package com.degiro.marketabuse.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CsvUtils {

    private static CsvMapper csvMapper;

    public static <T> ObjectReader getReader(Class<T> clazz, boolean withHeader, @NotNull Character separator) {
        ObjectReader reader = getOrCreateMapper().readerFor(clazz);
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
            csvMapper = CsvMapper.builder()
                                 .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                                 .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                                 .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                                 .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                 .build();
            csvMapper.findAndRegisterModules();
            csvMapper.registerModule(new JavaTimeModule());

            var module = getStringDeserializerModule();
            csvMapper.registerModule(module);
        }
        return csvMapper;
    }

    private static SimpleModule getStringDeserializerModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new StdDeserializer<>(String.class) {

            @Override
            public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                String result = StringDeserializer.instance.deserialize(p, ctxt);
                if (StringUtils.isEmpty(result)) {
                    return null;
                }
                return result;
            }
        });

        return simpleModule;
    }
}
