package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.adt.MyList;
import model.adt.MyStack;
import model.var.Value;


import java.io.BufferedReader;
import java.io.IOException;

public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public synchronized ProgramState execute(ProgramState p) throws MyException, IOException {
        synchronized (this) {
            MyHeap heap = p.getHeapTable();

            MyDictionary<String, BufferedReader> fileTable = p.getFileTable();

            MyStack<IStatement> exeStack = new MyStack<>();

            MyList<Value> outputList = p.getOut();

            // creating a deepcopy of the symbol table
            MyDictionary<String, Value> symbolTable = p.getSymTable();

            MyDictionary<String, Value> newSymbolTable = new MyDictionary<>();

            for(String key: symbolTable.getKeys()){
                Value valueCopy = symbolTable.lookup(key).deepCopy();
                newSymbolTable.add(key, valueCopy);
            }
            return new ProgramState(exeStack, newSymbolTable, outputList, fileTable, heap, this.statement);
        }
    }

    public String toString(){
        return "fork(" + this.statement.toString() + ")";
    }

}
