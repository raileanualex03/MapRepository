package repository;

import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.statement.IStatement;

import java.util.ArrayList;

public class Repository implements IRepository{
    ArrayList<ProgramState> programs;
    int currentProgram;

    public Repository(IStatement program){
        programs = new ArrayList<>();
        ProgramState mainProgram = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), program);
        programs.add(mainProgram);
        currentProgram = 0;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programs.get(currentProgram);
    }
}
