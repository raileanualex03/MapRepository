package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.var.Value;

public interface Expression {
    Value eval(MyDictionary<String, Value> table) throws MyException;
}
