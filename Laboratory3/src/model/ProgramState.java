package model;

import model.adt.*;
import model.statement.IStatement;
import model.var.Value;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    MyStack<IStatement> exeStack;
    MyDictionary<String, Value> symTable;
    MyList<Value> out;
    MyDictionary<String, BufferedReader> fileTable;
    IStatement originalProgram;
    MyHeap heapTable;


    public ProgramState(MyStack<IStatement> stack, MyDictionary<String, Value> table
            , MyList<Value> output, MyDictionary<String, BufferedReader> fileTable, MyHeap heap, IStatement op){
        exeStack = stack;
        symTable = table;
        out = output;
        this.fileTable = fileTable;
        heapTable = heap;
        originalProgram = op;
        if (originalProgram != null)
            stack.push(originalProgram);
    }

    public MyStack<IStatement> getExeStack() {
        return exeStack;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public MyDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyList<Value> getOut() {
        return out;
    }

    public MyDictionary<String, BufferedReader> getFileTable(){
        return fileTable;
    }

    public MyHeap getHeapTable(){
        return heapTable;
    }
    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public void setOut(MyList<Value> out) {
        this.out = out;
    }

    public void setSymTable(MyDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    @Override
    public String toString(){
        return "Program state " + '\n' +
                "exeStack=" + exeStack.toString() + '\n' +
                "symTable=" + symTable.toString() + '\n' +
                "out=" + out.toString() + '\n' +
                "fileTable = " + fileTable.toString() + '\n' +
                "heapTable=" + heapTable.toString();
    }
}

