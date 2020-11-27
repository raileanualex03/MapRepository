package model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.exp.Expression;
import model.types.RefType;
import model.var.RefValue;
import model.var.Value;

public class HeapReadingExpression implements Expression {
    private final Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyDictionary<String, Value> symTable, MyHeap heapTable) throws MyException {
        Value result = this.expression.eval(symTable, heapTable);
        if(!(result.getType() instanceof RefType))
            throw new MyException("result is wrong type!");
        RefValue refResult = (RefValue) result;
        if( !heapTable.containsKey(refResult.getAddress()))
            throw new MyException("heapTable doesnt contain this address");
        return heapTable.search(refResult.getAddress());
    }

    public String toString(){
        return "heapReading : " + this.expression.toString();
    }
}
