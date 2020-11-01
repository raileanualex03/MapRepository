package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.var.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value val){
        value = val;
    }

    @Override
    public Value eval(MyDictionary<String, Value> table) throws MyException {
        return value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
