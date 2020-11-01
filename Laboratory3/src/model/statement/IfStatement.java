package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyStack;
import model.exp.Expression;
import model.types.BoolType;
import model.var.BoolValue;
import model.var.Value;

public class IfStatement implements IStatement {
    final private Expression expression;
    final private IStatement thenStatement;
    final private  IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }


    @Override
    public ProgramState execute(ProgramState ps) throws MyException {
        MyStack<IStatement> stack = ps.getExeStack();
        MyDictionary<String, Value> table = ps.getSymTable();

        Value value = null;
        try {
            value = expression.eval(table);
        } catch (MyException e) {
            e.printStackTrace();
        }
        BoolValue booleanValue = new BoolValue(true);
        if (value.getType().equals(new BoolType())) {
            if (value.equals(booleanValue))
                stack.push(thenStatement);
            else
                stack.push(elseStatement);
        } else
            throw new MyException("Error: Expression not boolean");

        return ps;
    }

    public String toString(){
        return "if " + expression.toString() + " then " + thenStatement.toString() + " else " + elseStatement;
    }
}
