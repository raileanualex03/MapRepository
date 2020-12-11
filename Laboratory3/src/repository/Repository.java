package repository;

import exceptions.MyException;
import model.ProgramState;
import model.adt.*;
import model.statement.IStatement;

import java.io.*;
import java.util.ArrayList;

public class Repository implements IRepository{
    ArrayList<ProgramState> programs;
    int currentProgram;
    String logFilePath;

    public Repository(IStatement program, String filepath){
        programs = new ArrayList<>();
        logFilePath = filepath;
        ProgramState mainProgram = new ProgramState( new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), program);
        programs.add(mainProgram);
        currentProgram = 0;
    }

//    @Override
//    public ProgramState getCurrentProgram() {
//        return programs.get(currentProgram);
//    }

    @Override
    public void logProgramStateExecute(ProgramState program) throws MyException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(new File(logFilePath), true)));
        logFile.println(program.toString());
        logFile.close();
    }

    @Override
    public ArrayList<ProgramState> getProgramsList() {
        return programs;
    }

    @Override
    public void setProgramList(ArrayList<ProgramState> programList) {
        this.programs = programList;
    }

    public void addProgram(ProgramState program){
        programs.add(program);
        currentProgram += 1;
    }


}
