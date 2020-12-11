package repository;

import exceptions.MyException;
import model.ProgramState;
import model.adt.IList;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepository {

    //ProgramState getCurrentProgram();
    void logProgramStateExecute(ProgramState ps) throws MyException, IOException;
    ArrayList<ProgramState> getProgramsList();
    void setProgramList(ArrayList<ProgramState> programList);
}
