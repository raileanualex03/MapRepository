package repository;

import exceptions.MyException;
import model.ProgramState;

import java.io.IOException;

public interface IRepository {

    ProgramState getCurrentProgram();
    void logProgramStateExecute() throws MyException, IOException;
}
