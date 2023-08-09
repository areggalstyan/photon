package com.aregcraft.photon;

import com.aregcraft.photon.exception.UnknownOperatorException;
import com.aregcraft.photon.expression.*;

public class Evaluator implements ExpressionVisitor<Double> {
    private final Expression expression;
    private final EvaluationContext context;

    public Evaluator(Expression expression, EvaluationContext context) {
        this.expression = expression;
        this.context = context;
    }

    public double evaluate() {
        return expression.accept(this);
    }

    @Override
    public Double visitNumber(NumberExpression expression) {
        return expression.getValue();
    }

    @Override
    public Double visitArgument(ArgumentExpression expression) {
        return context.getArgument(expression.getName());
    }

    @Override
    public Double visitGrouping(GroupingExpression expression) {
        return expression.getExpression().accept(this);
    }

    @Override
    public Double visitBinary(BinaryExpression expression) {
        var left = expression.getLeft().accept(this);
        var right = expression.getRight().accept(this);
        return switch (expression.getOperator()) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case ASTERISK -> left * right;
            case SLASH -> left / right;
            case CARET -> Math.pow(left, right);
            default -> throw new UnknownOperatorException();
        };
    }

    @Override
    public Double visitUnary(UnaryExpression expression) {
        var right = expression.getRight().accept(this);
        return switch (expression.getOperator()) {
            case PLUS -> right;
            case MINUS -> -right;
            default -> throw new UnknownOperatorException();
        };
    }
}
