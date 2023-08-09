package com.aregcraft.photon;

import com.aregcraft.photon.exception.MissingParenthesisException;
import com.aregcraft.photon.exception.UnexpectedTokenException;
import com.aregcraft.photon.expression.*;
import com.aregcraft.photon.token.ArgumentToken;
import com.aregcraft.photon.token.NumberToken;
import com.aregcraft.photon.token.TokenStream;
import com.aregcraft.photon.token.TokenType;

import java.util.function.Supplier;

public class Parser {
    private final TokenStream input;

    public Parser(TokenStream input) {
        this.input = input;
    }

    public Expression parse() {
        return additionAndSubtraction();
    }

    private Expression additionAndSubtraction() {
        return binary(this::multiplicationAndDivision, TokenType.PLUS, TokenType.MINUS);
    }

    private Expression multiplicationAndDivision() {
        return binary(this::pow, TokenType.ASTERISK, TokenType.SLASH);
    }

    private Expression pow() {
        return binary(this::unary, TokenType.CARET);
    }

    private Expression binary(Supplier<Expression> next, TokenType... tokenTypes) {
        var left = next.get();
        while (input.checkNext(tokenTypes)) {
            left = new BinaryExpression(left, input.next().getType(), next.get());
        }
        return  left;
    }

    private Expression unary() {
        if (input.checkNext(TokenType.MINUS)) {
            return new UnaryExpression(input.next().getType(), unary());
        }
        return grouping();
    }

    private Expression grouping() {
        if (input.checkNext(TokenType.NUMBER)) {
            return new NumberExpression(((NumberToken) input.next()).getValue());
        }
        if (input.checkNext(TokenType.ARGUMENT)) {
            return new ArgumentExpression(((ArgumentToken) input.next()).getName());
        }
        if (!input.checkNext(TokenType.LEFT_PARENTHESIS)) {
            throw new UnexpectedTokenException();
        }
        input.next();
        var expression = parse();
        if (!input.checkNext(TokenType.RIGHT_PARENTHESIS)) {
            throw new MissingParenthesisException();
        }
        input.next();
        return new GroupingExpression(expression);
    }
}
