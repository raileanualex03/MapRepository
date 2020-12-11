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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    ExecutorService executor;


    public Controller(IRepository repo){
        repository = repo;
    }



//    public void allStep() throws MyException, IOException {
//        ProgramState program = repository.getCurrentProgram();
//        System.out.println(this.repository.getCurrentProgram());
//        while (!program.getExeStack().isEmpty()){
//            oneStep(program);
//            System.out.println("______________");
//            System.out.println(this.repository.getCurrentProgram());
//            program.getHeapTable().setContent(
//                    safeGarbageCollector(
//                            getAddrFromSymTable(program.getSymTable().getContent()),
//                            program.getHeapTable().getContent()
//                    )
//            );
//            // TODO: make sure that this function receives as parameter a program state
//            repository.logProgramStateExecute();
//        }
//    }

    public void oneStepForAllPrograms(List<ProgramState> programs) throws InterruptedException, MyException {
        programs.forEach(prg-> {
            try {
                repository.logProgramStateExecute(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = programs.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future-> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.print("");
                        }
                    return null;
                })
                //.filter(p->p!=null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        programs.addAll(newProgramsList);
        programs.forEach(prg-> {
            try {
                repository.logProgramStateExecute(prg);
            } catch (MyException | IOException e) {
                System.out.println(e);
            }
        });

        repository.setProgramList((ArrayList<ProgramState>) programs);
    }

    public void allStep() throws InterruptedException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(repository.getProgramsList());
            while (programList.size() > 0) {
                garbageCollectorAll(programList);
                oneStepForAllPrograms(programList);
                programList = removeCompletedPrograms(repository.getProgramsList());
            }


        executor.shutdownNow();

        repository.setProgramList((ArrayList<ProgramState>) programList);
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

    List<Integer> getAllAddressesAllSymbolTables(List<ProgramState> programStates){
        List<Integer> addressesList = new LinkedList<>();
        programStates.forEach(state -> addressesList.addAll(getAllValidAddresses
                (getAddressFromSymTable(state.getSymTable().getContent()), state.getHeapTable().getContent())));
        return addressesList;
    }
    public void garbageCollectorAll(List<ProgramState> programStates){
        List<Integer> addresses = getAllAddressesAllSymbolTables(programStates);
        programStates.forEach(state -> state.getHeapTable().setContent(safeGarbageCollector(addresses, state.getHeapTable().getContent())));
    }

    List<Integer> getAllValidAddresses(List<Integer> symbolTableAddresses, Map<Integer, Value> heap) {
        var heapEntrySet = heap.entrySet();
        LinkedList<Integer> addressesList = new LinkedList<Integer>(symbolTableAddresses);

        boolean filteredAll = false;
        while (!filteredAll) {
            filteredAll = true;
            List <Integer> currentAddresses = heapEntrySet.stream()
                    .filter(entry -> addressesList.contains(entry.getKey()))
                    .filter(entry -> entry.getValue() instanceof RefValue)
                    .map(entry -> {RefValue value = (RefValue) entry.getValue();
                        return value.getAddress();})
                    .filter(entry -> !addressesList.contains(entry))
                    .collect(Collectors.toList());

            if (!currentAddresses.isEmpty()) {
                addressesList.addAll(currentAddresses);
                filteredAll = false;
            }
        }
        return addressesList;
    }

    public List<Integer> getAddressFromSymTable(Collection<Value> symTable){
        return symTable.stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList){
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

}
