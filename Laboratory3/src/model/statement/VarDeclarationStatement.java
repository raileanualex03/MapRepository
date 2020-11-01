package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
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
        switch(type.toString()){
            case "Boolean" -> value = new BoolValue(false);
            case "Integer" -> value = new IntValue(0);
            default -> throw new MyException("Error: Bad type");
        }

        if (table.isDefined(name))
                throw new MyException("Variable already exists: " + name);
        table.add(name, value);
        return p;

    }

    public String toString(){
        return type + " " + name;
    }
}
