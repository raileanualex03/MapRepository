package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.types.BoolType;
import model.var.BoolValue;
import model.var.Value;

public class LogicExpression implements Expression{
    Expression firstExpression, secondExpression;
    int operation;

    public LogicExpression(int op, Expression e1, Expression e2){
        this.operation = op; // 1 is && and 2 is ||
        this.firstExpression = e1;
        this.secondExpression = e2;
    }

    @Override
    public Value eval(MyDictionary<String, Value> table) throws MyException {
        Value firstValue, secondValue;
        firstValue = firstExpression.eval(table);
        secondValue = secondExpression.eval(table);
        if (operation != 2 && operation != 1)
            throw new MyException("Invalid operation");
        if (!firstValue.getType().equals(new BoolType()))
            throw new MyException("Error: first value is not boolean");
        if (!secondValue.getType().equals(new BoolType()))
            throw new MyException("Error: second value is not boolean");
        BoolValue first = (BoolValue) firstValue;
        BoolValue second = (BoolValue) secondValue;
        boolean firstBoolean, secondBoolean;
        firstBoolean = first.getValue();
        secondBoolean = second.getValue();

        return switch (operation) {
            case 1 -> new BoolValue(firstBoolean && secondBoolean);
            case 2 -> new BoolValue(firstBoolean || secondBoolean);
            default -> throw new MyException("Error: Bad operation");
        };

    }
}
