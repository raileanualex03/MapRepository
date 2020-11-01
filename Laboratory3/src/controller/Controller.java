package controller;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyStack;
import model.statement.IStatement;
import repository.IRepository;

public class Controller {
    IRepository repository;

    public Controller(IRepository repo){
        repository = repo;
    }

    public ProgramState oneStep(ProgramState state) throws MyException {
        MyStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new MyException("Program stack is empty");
        IStatement statement = stack.pop();

        return statement.execute(state);

    }

    public void allStep() throws MyException {
        ProgramState program = repository.getCurrentProgram();
        System.out.println(this.repository.getCurrentProgram());
        while (!program.getExeStack().isEmpty()){
            oneStep(program);
            System.out.println("______________");
            System.out.println(this.repository.getCurrentProgram());
        }
    }

    public ProgramState getMainProgram(){
        return this.repository.getCurrentProgram();
    }

}
