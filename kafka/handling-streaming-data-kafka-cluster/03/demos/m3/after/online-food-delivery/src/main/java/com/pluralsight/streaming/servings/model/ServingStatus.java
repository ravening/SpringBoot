/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.pluralsight.streaming.servings.model;
@org.apache.avro.specific.AvroGenerated
public enum ServingStatus implements org.apache.avro.generic.GenericEnumSymbol<ServingStatus> {
  CREATED, COOKING, DONE  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"ServingStatus\",\"namespace\":\"com.pluralsight.streaming.servings.model\",\"symbols\":[\"CREATED\",\"COOKING\",\"DONE\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
