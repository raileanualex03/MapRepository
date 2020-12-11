package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.exp.Expression;
import model.types.StringType;
import model.var.StringValue;
import model.var.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement{
    private final Expression expression;

    public CloseRFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState p) throws MyException, IOException {
        MyDictionary<String, Value> table = p.getSymTable();
        MyDictionary<String, BufferedReader> fileTable = p.getFileTable();
        Value value = expression.eval(table, p.getHeapTable());
        if (!value.getType().equals(new StringType()))
            throw new MyException("Wrong type");
        StringValue val = (StringValue)value;
        BufferedReader reader = fileTable.lookup(((StringValue)value).getValue());
        reader.close();
        fileTable.remove(val.getValue());
        return null;
    }

    public String toString(){
        return "Close file(" + expression.toString() + ")";
    }
}
