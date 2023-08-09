package com.aregcraft.photon.token;

import java.util.List;

public class TokenStream {
    private final List<Token> elements;
    private int index;

    public TokenStream(List<Token> elements) {
        this.elements = elements;
    }

    public boolean hasNext() {
        return index < elements.size();
    }

    public Token peek() {
        return elements.get(index);
    }

    public Token next() {
        return elements.get(index++);
    }

    public boolean checkNext(TokenType... types) {
        return hasNext() && List.of(types).contains(peek().getType());
    }
}
