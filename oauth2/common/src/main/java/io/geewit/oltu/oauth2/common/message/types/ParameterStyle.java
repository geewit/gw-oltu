package io.geewit.oltu.oauth2.common.message.types;

import io.geewit.core.utils.lang.enums.Name;

/**
 *
 */
public enum ParameterStyle implements Name {
    BODY("body"),
    QUERY("query"),
    HEADER("header");

    private String name;

    ParameterStyle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
