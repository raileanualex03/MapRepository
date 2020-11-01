package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.IStack;

public class CompStatement implements IStatement{

    final private IStatement first;
    final private IStatement second;

    public CompStatement(IStatement f, IStatement s){
        first = f;
        second = s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

    @Override
    public String toString(){
        return '(' + this.first.toString() + ',' + this.second.toString() + ')';
    }
}
