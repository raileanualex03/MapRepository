package controller;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.statement.IStatement;
import model.types.RefType;
import model.var.RefValue;
import model.var.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
            program.getHeapTable().setContent(
                    safeGarbageCollector(
                            getAddrFromSymTable(program.getSymTable().getContent()),
                            program.getHeapTable().getContent()
                    )
            );

            repository.logProgramStateExecute();
        }
    }

    public ProgramState getMainProgram(){
        return this.repository.getCurrentProgram();
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> systemTableAddress, HashMap<Integer, Value> heap){
        Set<Integer> heapAddresses = heap.keySet();
        MyList<Integer> heapReferenceAddresses = new MyList<>();
        for (int key: heapAddresses){
            Value currentValue = heap.get(key);
            if (currentValue.getType() instanceof RefType){
                RefValue referenceValue = (RefValue) currentValue;
                heapReferenceAddresses.add(referenceValue.getAddress());
            }
        }
        for (Integer heapAddress: heapAddresses){
            if(!systemTableAddress.contains(heapAddress))
                if(!heapReferenceAddresses.contains(heapAddress))
                    heap.remove(heapAddress);
        }

        return heap;
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTable){
        return symTable.stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
}
