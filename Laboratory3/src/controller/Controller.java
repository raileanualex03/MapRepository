package controller;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyStack;
import model.statement.IStatement;
import repository.IRepository;

import java.io.IOException;

public class Controller {
    IRepository repository;

    public Controller(IRepository repo){
        repository = repo;
    }

    public ProgramState oneStep(ProgramState state) throws MyException, IOException {
        MyStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new MyException("Program stack is empty");
        IStatement statement = stack.pop();
        System.out.println(this.repository.getCurrentProgram());
        return statement.execute(state);

    }

    public void allStep() throws MyException, IOException {
        ProgramState program = repository.getCurrentProgram();
        System.out.println(this.repository.getCurrentProgram());
        while (!program.getExeStack().isEmpty()){
            oneStep(program);
            System.out.println("______________");
            System.out.println(this.repository.getCurrentProgram());
            repository.logProgramStateExecute();
        }
    }

    public ProgramState getMainProgram(){
        return this.repository.getCurrentProgram();
    }

}
