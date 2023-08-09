package com.aregcraft.photon.token;

public class ArgumentToken extends Token {
    private final String name;

    public ArgumentToken(String name) {
        super(TokenType.ARGUMENT);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
