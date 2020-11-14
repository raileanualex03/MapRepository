package model.statement;

import exceptions.MyException;
import model.ProgramState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState p) throws MyException, IOException;

}
