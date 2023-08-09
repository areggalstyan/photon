package com.aregcraft.photon.expression;

import com.aregcraft.photon.token.TokenType;

public class UnaryExpression implements Expression {
    private final TokenType operator;
    private final Expression right;

    public UnaryExpression(TokenType operator, Expression right) {
        this.operator = operator;
        this.right = right;
    }

    public TokenType getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitUnary(this);
    }
}
