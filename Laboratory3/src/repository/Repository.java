package repository;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.statement.IStatement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements IRepository{
    ArrayList<ProgramState> programs;
    int currentProgram;
    String logFilePath;

    public Repository(IStatement program, String filepath){
        programs = new ArrayList<>();
        logFilePath = filepath;
        ProgramState mainProgram = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), program);
        programs.add(mainProgram);
        currentProgram = 0;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programs.get(currentProgram);
    }

    @Override
    public void logProgramStateExecute() throws MyException, IOException {
        String logFilePath = "";
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.print(programs.get(currentProgram).toString());
    }

    public void addProgram(ProgramState program){
        programs.add(program);
        currentProgram += 1;
    }


}
