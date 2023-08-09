package com.aregcraft.photon.expression;

public interface ExpressionVisitor<T> {
    T visitNumber(NumberExpression expression);

    T visitArgument(ArgumentExpression expression);

    T visitGrouping(GroupingExpression expression);

    T visitBinary(BinaryExpression expression);

    T visitUnary(UnaryExpression expression);
}
