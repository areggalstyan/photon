package com.aregcraft.photon;

import com.aregcraft.photon.token.*;

import java.util.ArrayList;

public class Tokenizer {
    private final String input;
    private int current;

    public Tokenizer(String input) {
        this.input = input;
    }

    public TokenStream tokenize() {
        var tokens = new ArrayList<Token>();
        while (hasNext()) {
            if (Character.isWhitespace(peek())) {
                continue;
            }
            var byChar = TokenType.getByChar(peek());
            if (byChar != null) {
                tokens.add(new Token(byChar));
                next();
                continue;
            }
            if (Character.isDigit(peek())) {
                tokens.add(new NumberToken(parseNumber()));
                continue;
            }
            tokens.add(new ArgumentToken(parseName()));
        }
        return new TokenStream(tokens);
    }

    private double parseNumber() {
        var start = current;
        while (hasNext() && Character.isDigit(peek())) {
            next();
        }
        if (hasNext() && peek() != '.') {
            while (hasNext() && Character.isDigit(peek())) {
                next();
            }
        }
        return Double.parseDouble(input.substring(start, current));
    }

    private String parseName() {
        var start = current;
        while (hasNext() && Character.isLetterOrDigit(peek())) {
            next();
        }
        return input.substring(start, current);
    }

    private boolean hasNext() {
        return current < input.length();
    }

    private char peek() {
        return input.charAt(current);
    }

    private void next() {
        current++;
    }
}
