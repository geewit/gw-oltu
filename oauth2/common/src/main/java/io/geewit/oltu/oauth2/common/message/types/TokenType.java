package io.geewit.oltu.oauth2.common.message.types;

import io.geewit.core.utils.enums.Name;

/**
 *
 */
public enum TokenType implements Name {
    BEARER("Bearer"),
    MAC("MAC");

    private String name;

    TokenType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
