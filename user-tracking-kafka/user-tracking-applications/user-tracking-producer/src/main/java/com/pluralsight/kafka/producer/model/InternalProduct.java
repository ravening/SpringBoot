package com.pluralsight.kafka.producer.model;

import com.pluralsight.kafka.producer.enums.Color;
import com.pluralsight.kafka.producer.enums.ProductType;
import com.pluralsight.kafka.producer.enums.DesignType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternalProduct {

    private Color color;

    private ProductType type;

    private DesignType designType;

}
