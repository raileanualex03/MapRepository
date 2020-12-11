package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.adt.MyStack;
import model.exp.Expression;
import model.var.BoolValue;
import model.var.Value;

import java.io.IOException;

public class WhileStatement implements  IStatement{
    Expression expression;
    IStatement statement;

    public WhileStatement(Expression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState p) throws MyException, IOException {
        MyDictionary<String, Value> symTable = p.getSymTable();
        MyStack<IStatement> exeStack = p.getExeStack();
        MyHeap heap = p.getHeapTable();
        Value result = expression.eval(symTable, heap);
        if (result.equals(new BoolValue(true))) {
            exeStack.push(new WhileStatement(expression, statement));
            exeStack.push(statement);
        }

        return null;
    }

    public String toString(){
        return "While (" + this.expression.toString() + ") do: " + this.statement.toString();
    }
}
