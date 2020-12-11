package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.exp.Expression;
import model.types.IntType;
import model.var.IntValue;
import model.var.StringValue;
import model.var.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    private final Expression expression;
    private final String var_name;

    public ReadFileStatement(Expression expression, String var_name) {
        this.expression = expression;
        this.var_name = var_name;
    }


    @Override
    public ProgramState execute(ProgramState p) throws MyException, IOException {
        MyDictionary<String, Value> symTable = p.getSymTable();
        MyDictionary<String, BufferedReader> table = p.getFileTable();

        if (table.isDefined(var_name))
            throw new MyException("Variable not defined: " + var_name);
        if (!(symTable.lookup(var_name)).getType().equals(new IntType()))
            throw new MyException("Wrong type");
        Value result = expression.eval(symTable, p.getHeapTable());
        StringValue resultString = (StringValue)result;
        BufferedReader reader = table.lookup(resultString.getValue());
        String line = reader.readLine();
        int value = 0;
        if (line != null){
            value = Integer.parseInt(line);
        }
        Value val = new IntValue(value);
        symTable.update(var_name, val);

        return null;
    }

    @Override
    public String toString(){
        return (this.var_name + " = readLine(" + this.expression.toString() + ")");
    }

}
