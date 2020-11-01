package model.statement;

import exceptions.MyException;
import model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState p) throws MyException;

}
