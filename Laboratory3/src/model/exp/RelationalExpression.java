package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.types.IntType;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.Value;

public class RelationalExpression implements Expression{
    public final String operation;
    public final Expression firstExpression;
    public final Expression secondExpression;

    public RelationalExpression(String operation, Expression firstExpression, Expression secondExpression) {
        this.operation = operation;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }


    @Override
    public Value eval(MyDictionary<String, Value> table) throws MyException {
        Value firstResult = firstExpression.eval(table);
        Value secondResult = secondExpression.eval(table);
        if(!firstResult.getType().equals(new IntType()))
            throw new MyException("Invalid type");
        if(!secondResult.getType().equals(new IntType()))
            throw new MyException("Invalid type");


        IntValue firstValue = (IntValue) firstResult;
        IntValue secondValue = (IntValue) secondResult;
        return switch (operation) {
            case "<" -> new BoolValue(firstValue.getValue() < secondValue.getValue());
            case "<=" -> new BoolValue(firstValue.getValue() <= secondValue.getValue());
            case ">" -> new BoolValue(firstValue.getValue() > secondValue.getValue());
            case ">=" -> new BoolValue(firstValue.getValue() >= secondValue.getValue());
            case "==" -> new BoolValue(firstValue.getValue() == secondValue.getValue());
            case "!=" -> new BoolValue(firstValue.getValue() != secondValue.getValue());
            default -> throw new MyException("Wrong operation type!");
        };
    }

    public String toString(){
        return switch (operation) {
            case "<" -> firstExpression + "<" + secondExpression;
            case "<=" -> firstExpression + "<=" + secondExpression;
            case "==" -> firstExpression + "==" + secondExpression;
            case "!=" -> firstExpression + "!=" + secondExpression;
            case ">" -> firstExpression + ">" + secondExpression;
            case ">=" -> firstExpression + ">=" + secondExpression;
            default -> "Bad operation";
        };
    }
}
