package io.geewit.oltu.commons.json;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CustomizableBuilder<E> {

    /**
     * The registry that keeps the custom fields.
     */
    private final Map<String, Object> customFields = new LinkedHashMap<>();

    protected final Map<String, Object> getCustomFields() {
        return customFields;
    }

    /**
     * TODO
     *
     * @param key
     * @param value
     * @return
     */
    public final CustomizableBuilder<E> setCustomField(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed.");
        }
        if (value != null) {
            customFields.put(key, value);
        }
        return this;
    }

    public abstract E build();

}
