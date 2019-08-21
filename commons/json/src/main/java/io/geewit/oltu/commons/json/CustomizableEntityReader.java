package io.geewit.oltu.commons.json;

import javax.json.*;
import java.io.StringReader;
import java.util.Map.Entry;

import static java.lang.String.format;

/**
 * TODO
 */
public abstract class CustomizableEntityReader<E, B extends CustomizableBuilder<E>> {

    private final B builder;

    public CustomizableEntityReader(B builder) {
        this.builder = builder;
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

    protected final B getBuilder() {
        return builder;
    }

    /**
     * @param jsonString
     */
    public void read(String jsonString) {
        if (jsonString == null) {
            throw new IllegalArgumentException("Null string does not represent a valid JSON object");
        }

        StringReader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        JsonStructure structure = jsonReader.read();

        if (structure == null || structure instanceof JsonArray) {
            throw new IllegalArgumentException(format("String '%s' is not a valid JSON object representation",
                    jsonString));
        }

        JsonObject object = (JsonObject) structure;
        for (Entry<String, JsonValue> entry : object.entrySet()) {
            String key = entry.getKey();
            JsonValue jsonValue = entry.getValue();

            // guard from null values
            if (jsonValue != null) {
                Object value = toJavaObject(jsonValue);

                // if the concrete implementation is not able to handle the property, set the custom field
                if (!handleProperty(key, value)) {
                    builder.setCustomField(key, value);
                }
            }
        }

        jsonReader.close();
    }

    protected abstract <T> boolean handleProperty(String key, T value);

}
