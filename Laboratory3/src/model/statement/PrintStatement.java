package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.exp.Expression;
import model.var.Value;

public class PrintStatement implements IStatement{
    final private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState p) throws MyException {
        MyList<Value> output = p.getOut();
        MyDictionary<String, Value> table = p.getSymTable();
        try {
            output.add(expression.eval(table, p.getHeapTable()));
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString(){
        return "print " + expression.toString();
    }
}
