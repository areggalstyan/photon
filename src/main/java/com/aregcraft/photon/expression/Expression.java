package com.aregcraft.photon.expression;

public interface Expression {
    <T> T accept(ExpressionVisitor<T> visitor);
}
