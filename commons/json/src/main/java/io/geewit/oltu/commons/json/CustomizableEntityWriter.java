package io.geewit.oltu.commons.json;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map.Entry;

public abstract class CustomizableEntityWriter<CE extends CustomizableEntity> {

    private final StringWriter stringWriter = new StringWriter();

    private final JsonGenerator generator = Json.createGenerator(stringWriter);

    public final String write(CE customizableEntity) {
        generator.writeStartObject();

        handleProperties(customizableEntity);

        for (Entry<String, Object> customFields : customizableEntity.getCustomFields()) {
            set(customFields.getKey(), customFields.getValue());
        }

        generator.writeEnd().close();

        return stringWriter.toString();
    }

    protected abstract void handleProperties(CE customizableEntity);

    protected final <T> void set(String key, T value) {
        if (key != null && value != null) {
            if (value instanceof Boolean) {
                generator.write(key, (Boolean) value);
            } else if (value instanceof Double) {
                generator.write(key, (Double) value);
            } else if (value instanceof Integer) {
                generator.write(key, (Integer) value);
            } else if (value instanceof BigDecimal) {
                generator.write(key, (BigDecimal) value);
            } else if (value instanceof BigInteger) {
                generator.write(key, (BigInteger) value);
            } else if (value instanceof Long) {
                generator.write(key, (Long) value);
            } else if (value instanceof String) {
                String string = (String) value;
                if (!string.isEmpty()) {
                    generator.write(key, string);
                }
            }
        }
    }

    protected final <T> void set(String key, T[] value) {
        if (value == null) {
            return;
        }

        generator.writeStartArray(key);

        for (T item : value) {
            if (item != null) {
                if (item instanceof Boolean) {
                    generator.write((Boolean) item);
                } else if (item instanceof Double) {
                    generator.write((Double) item);
                } else if (item instanceof Integer) {
                    generator.write((Integer) item);
                } else if (item instanceof BigDecimal) {
                    generator.write((BigDecimal) item);
                } else if (item instanceof BigInteger) {
                    generator.write((BigInteger) item);
                } else if (item instanceof Long) {
                    generator.write((Long) item);
                } else if (item instanceof String) {
                    generator.write((String) item);
                }
            }
        }

        generator.writeEnd();
    }

}
