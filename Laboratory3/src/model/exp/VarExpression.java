package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.var.Value;

public class VarExpression implements Expression {
    String id;

    public VarExpression(String id){
        this.id = id;
    }


    @Override
    public Value eval(MyDictionary<String, Value> table) throws MyException {
        return table.lookup(id);
    }

    public String toString(){
        return id;
    }
}

