/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.pluralsight.streaming.servings.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Serving extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5624578640075603533L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Serving\",\"namespace\":\"com.pluralsight.streaming.servings.model\",\"fields\":[{\"name\":\"foodType\",\"type\":\"string\"},{\"name\":\"servingStatus\",\"type\":{\"type\":\"enum\",\"name\":\"ServingStatus\",\"symbols\":[\"CREATED\",\"COOKING\",\"DONE\"]}},{\"name\":\"size\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Serving> ENCODER =
      new BinaryMessageEncoder<Serving>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Serving> DECODER =
      new BinaryMessageDecoder<Serving>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Serving> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Serving> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Serving> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Serving>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Serving to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Serving from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Serving instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Serving fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence foodType;
  @Deprecated public com.pluralsight.streaming.servings.model.ServingStatus servingStatus;
  @Deprecated public int size;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Serving() {}

  /**
   * All-args constructor.
   * @param foodType The new value for foodType
   * @param servingStatus The new value for servingStatus
   * @param size The new value for size
   */
  public Serving(java.lang.CharSequence foodType, com.pluralsight.streaming.servings.model.ServingStatus servingStatus, java.lang.Integer size) {
    this.foodType = foodType;
    this.servingStatus = servingStatus;
    this.size = size;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return foodType;
    case 1: return servingStatus;
    case 2: return size;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: foodType = (java.lang.CharSequence)value$; break;
    case 1: servingStatus = (com.pluralsight.streaming.servings.model.ServingStatus)value$; break;
    case 2: size = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'foodType' field.
   * @return The value of the 'foodType' field.
   */
  public java.lang.CharSequence getFoodType() {
    return foodType;
  }


  /**
   * Sets the value of the 'foodType' field.
   * @param value the value to set.
   */
  public void setFoodType(java.lang.CharSequence value) {
    this.foodType = value;
  }

  /**
   * Gets the value of the 'servingStatus' field.
   * @return The value of the 'servingStatus' field.
   */
  public com.pluralsight.streaming.servings.model.ServingStatus getServingStatus() {
    return servingStatus;
  }


  /**
   * Sets the value of the 'servingStatus' field.
   * @param value the value to set.
   */
  public void setServingStatus(com.pluralsight.streaming.servings.model.ServingStatus value) {
    this.servingStatus = value;
  }

  /**
   * Gets the value of the 'size' field.
   * @return The value of the 'size' field.
   */
  public int getSize() {
    return size;
  }


  /**
   * Sets the value of the 'size' field.
   * @param value the value to set.
   */
  public void setSize(int value) {
    this.size = value;
  }

  /**
   * Creates a new Serving RecordBuilder.
   * @return A new Serving RecordBuilder
   */
  public static com.pluralsight.streaming.servings.model.Serving.Builder newBuilder() {
    return new com.pluralsight.streaming.servings.model.Serving.Builder();
  }

  /**
   * Creates a new Serving RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Serving RecordBuilder
   */
  public static com.pluralsight.streaming.servings.model.Serving.Builder newBuilder(com.pluralsight.streaming.servings.model.Serving.Builder other) {
    if (other == null) {
      return new com.pluralsight.streaming.servings.model.Serving.Builder();
    } else {
      return new com.pluralsight.streaming.servings.model.Serving.Builder(other);
    }
  }

  /**
   * Creates a new Serving RecordBuilder by copying an existing Serving instance.
   * @param other The existing instance to copy.
   * @return A new Serving RecordBuilder
   */
  public static com.pluralsight.streaming.servings.model.Serving.Builder newBuilder(com.pluralsight.streaming.servings.model.Serving other) {
    if (other == null) {
      return new com.pluralsight.streaming.servings.model.Serving.Builder();
    } else {
      return new com.pluralsight.streaming.servings.model.Serving.Builder(other);
    }
  }

  /**
   * RecordBuilder for Serving instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Serving>
    implements org.apache.avro.data.RecordBuilder<Serving> {

    private java.lang.CharSequence foodType;
    private com.pluralsight.streaming.servings.model.ServingStatus servingStatus;
    private int size;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.pluralsight.streaming.servings.model.Serving.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.foodType)) {
        this.foodType = data().deepCopy(fields()[0].schema(), other.foodType);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.servingStatus)) {
        this.servingStatus = data().deepCopy(fields()[1].schema(), other.servingStatus);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.size)) {
        this.size = data().deepCopy(fields()[2].schema(), other.size);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Serving instance
     * @param other The existing instance to copy.
     */
    private Builder(com.pluralsight.streaming.servings.model.Serving other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.foodType)) {
        this.foodType = data().deepCopy(fields()[0].schema(), other.foodType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.servingStatus)) {
        this.servingStatus = data().deepCopy(fields()[1].schema(), other.servingStatus);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.size)) {
        this.size = data().deepCopy(fields()[2].schema(), other.size);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'foodType' field.
      * @return The value.
      */
    public java.lang.CharSequence getFoodType() {
      return foodType;
    }


    /**
      * Sets the value of the 'foodType' field.
      * @param value The value of 'foodType'.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder setFoodType(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.foodType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'foodType' field has been set.
      * @return True if the 'foodType' field has been set, false otherwise.
      */
    public boolean hasFoodType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'foodType' field.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder clearFoodType() {
      foodType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'servingStatus' field.
      * @return The value.
      */
    public com.pluralsight.streaming.servings.model.ServingStatus getServingStatus() {
      return servingStatus;
    }


    /**
      * Sets the value of the 'servingStatus' field.
      * @param value The value of 'servingStatus'.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder setServingStatus(com.pluralsight.streaming.servings.model.ServingStatus value) {
      validate(fields()[1], value);
      this.servingStatus = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'servingStatus' field has been set.
      * @return True if the 'servingStatus' field has been set, false otherwise.
      */
    public boolean hasServingStatus() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'servingStatus' field.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder clearServingStatus() {
      servingStatus = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'size' field.
      * @return The value.
      */
    public int getSize() {
      return size;
    }


    /**
      * Sets the value of the 'size' field.
      * @param value The value of 'size'.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder setSize(int value) {
      validate(fields()[2], value);
      this.size = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'size' field has been set.
      * @return True if the 'size' field has been set, false otherwise.
      */
    public boolean hasSize() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'size' field.
      * @return This builder.
      */
    public com.pluralsight.streaming.servings.model.Serving.Builder clearSize() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Serving build() {
      try {
        Serving record = new Serving();
        record.foodType = fieldSetFlags()[0] ? this.foodType : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.servingStatus = fieldSetFlags()[1] ? this.servingStatus : (com.pluralsight.streaming.servings.model.ServingStatus) defaultValue(fields()[1]);
        record.size = fieldSetFlags()[2] ? this.size : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Serving>
    WRITER$ = (org.apache.avro.io.DatumWriter<Serving>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Serving>
    READER$ = (org.apache.avro.io.DatumReader<Serving>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.foodType);

    out.writeEnum(this.servingStatus.ordinal());

    out.writeInt(this.size);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.foodType = in.readString(this.foodType instanceof Utf8 ? (Utf8)this.foodType : null);

      this.servingStatus = com.pluralsight.streaming.servings.model.ServingStatus.values()[in.readEnum()];

      this.size = in.readInt();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.foodType = in.readString(this.foodType instanceof Utf8 ? (Utf8)this.foodType : null);
          break;

        case 1:
          this.servingStatus = com.pluralsight.streaming.servings.model.ServingStatus.values()[in.readEnum()];
          break;

        case 2:
          this.size = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









