package com.aregcraft.photon.expression;

public class ArgumentExpression implements Expression {
    private final String name;

    public ArgumentExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitArgument(this);
    }
}
