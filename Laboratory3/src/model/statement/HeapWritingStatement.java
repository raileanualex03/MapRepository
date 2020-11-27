package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.exp.Expression;
import model.types.RefType;
import model.var.RefValue;
import model.var.StringValue;
import model.var.Value;

import java.io.IOException;
import java.sql.Ref;

public class HeapWritingStatement implements IStatement{
    private final StringValue var_name;
    private final Expression expression;

    public HeapWritingStatement(StringValue var_name, Expression expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState p) throws MyException, IOException {
        MyDictionary<String, Value> symTable = p.getSymTable();
        MyHeap heapTable = p.getHeapTable();

        if(!symTable.isDefined(var_name.getValue()))
            throw new MyException("variable not defined!");

        Value result = symTable.lookup(var_name.getValue());
        if (!(result.getType() instanceof RefType))
            throw new MyException("not a reference");

        RefValue refResult = (RefValue)result;
        if (!heapTable.containsKey(refResult.getAddress()))
            throw new MyException("heapTable doesnt have this key");

        Value expressionResult = expression.eval(symTable, heapTable);
        RefType refResultType = (RefType)refResult.getType();

        if(!expressionResult.getType().equals(refResultType.getInner()))
            throw new MyException("result has different type ");

        heapTable.update(refResult.getAddress(), expressionResult);

        return p;
    }

    public String toString(){
        return "heapWriting(" + this.expression.toString() + ")";
    }
}
