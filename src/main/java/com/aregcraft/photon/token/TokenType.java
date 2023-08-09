package com.aregcraft.photon.token;

import java.util.Map;

public enum TokenType {
    NUMBER,
    ARGUMENT,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    ASTERISK,
    MINUS,
    PLUS,
    SLASH,
    CARET;

    private static final Map<Character, TokenType> BY_CHAR = Map.of(
            '(', LEFT_PARENTHESIS,
            ')', RIGHT_PARENTHESIS,
            '*', ASTERISK,
            '-', MINUS,
            '+', PLUS,
            '/', SLASH,
            '^', CARET
    );

    public static TokenType getByChar(char ch) {
        return BY_CHAR.get(ch);
    }
}
