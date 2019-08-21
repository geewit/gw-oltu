package io.geewit.oltu.oauth2.common.message.types;

import io.geewit.core.utils.lang.enums.Name;

/**
 *
 */
public enum ResponseType implements Name {

    CODE("code"),
    TOKEN("token");

    private String name;

    ResponseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
