package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.types.*;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.Value;

public class VarDeclarationStatement implements IStatement{
    final private String name;
    final private Type type;

    public VarDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }



    @Override
    public ProgramState execute(ProgramState p) throws MyException {
        MyDictionary<String, Value> table = p.getSymTable();
        Value value;
        if (table.isDefined(name))
            throw new MyException("Variable already exists: " + name);


        value = type.defaultValue();

        table.add(name, value);
        return null;

    }

    public String toString(){
        return type + " " + name;
    }
}
