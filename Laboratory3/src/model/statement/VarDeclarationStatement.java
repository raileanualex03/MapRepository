package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
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
        Type which;
        switch(type.toString()){
            case "Boolean" -> which = new BoolType();
            case "Integer" -> which = new IntType();
            case "String" -> which = new StringType();
            default -> throw new MyException("Error: Bad type");
        }
        value = which.defaultValue();

        if (table.isDefined(name))
                throw new MyException("Variable already exists: " + name);
        table.add(name, value);
        return p;

    }

    public String toString(){
        return type + " " + name;
    }
}
