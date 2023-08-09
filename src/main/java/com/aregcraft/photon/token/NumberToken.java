package com.aregcraft.photon.token;

public class NumberToken extends Token {
    private final double value;

    public NumberToken(double value) {
        super(TokenType.NUMBER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
