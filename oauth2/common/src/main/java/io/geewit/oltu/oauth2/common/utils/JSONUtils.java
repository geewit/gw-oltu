package io.geewit.oltu.oauth2.common.utils;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 *
 *
 *
 */
public final class JSONUtils {

    private static final JsonGeneratorFactory GENERATOR_FACTORY = Json.createGeneratorFactory(null);

    public static String buildJSON(Map<String, Object> params) {
        final StringWriter stringWriter = new StringWriter();
        final JsonGenerator generator = GENERATOR_FACTORY.createGenerator(stringWriter);

        generator.writeStartObject();

        for (Map.Entry<String, Object> param : params.entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
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
                } else if (value.getClass().isArray()) {
                    generator.writeStartArray(key);

                    IntStream.range(0, Array.getLength(value)).forEach(i -> witeItem(generator, Array.get(value, i)));

                    generator.writeEnd();
                } else if (value instanceof Collection) {
                    generator.writeStartArray(key);

                    Collection<?> collection = (Collection<?>) value;
                    collection.forEach(item -> witeItem(generator, item));

                    generator.writeEnd();
                }
            }
        }

        generator.writeEnd().close();

        return stringWriter.toString();
    }

    private static <T> void witeItem(JsonGenerator generator, T item) {
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

    public static Map<String, Object> parseJSON(String jsonBody) {
        final Map<String, Object> params = new HashMap<>();

        StringReader reader = new StringReader(jsonBody);
        JsonReader jsonReader = Json.createReader(reader);
        JsonStructure structure = jsonReader.read();

        if (structure == null || structure instanceof JsonArray) {
            throw new IllegalArgumentException(format("String '%s' is not a valid JSON object representation",
                    jsonBody));
        }

        JsonObject object = (JsonObject) structure;
        object.forEach((key, jsonValue) -> {
            if (key != null && !key.isEmpty()) {
                // guard from null values
                if (jsonValue != null) {
                    Object value = toJavaObject(jsonValue);
                    params.put(key, value);
                }
            }
        });

        jsonReader.close();
        return params;
    }

    private static Object toJavaObject(JsonValue jsonValue) {
        Object value = null;

        switch (jsonValue.getValueType()) {
            case ARRAY:
                JsonArray array = (JsonArray) jsonValue;
                Object[] values = new Object[array.size()];
                for (int i = 0; i < array.size(); i++) {
                    JsonValue current = array.get(i);
                    values[i] = toJavaObject(current);
                }
                value = values;
                break;

            case FALSE:
                value = false;
                break;

            case NULL:
                value = null;
                break;

            case NUMBER:
                JsonNumber jsonNumber = (JsonNumber) jsonValue;
                value = jsonNumber.numberValue();
                break;

            case OBJECT:
                // not supported in this version
                break;

            case STRING:
                JsonString jsonString = (JsonString) jsonValue;
                value = jsonString.getString();
                break;

            case TRUE:
                value = true;
                break;

            default:
                break;
        }

        return value;
    }

}
