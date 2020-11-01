package model.statement;

import exceptions.MyException;
import model.ProgramState;

public class NopStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState ps) throws MyException {
        return ps;
    }
}
