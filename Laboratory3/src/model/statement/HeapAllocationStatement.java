package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.exp.Expression;
import model.types.RefType;
import model.var.RefValue;
import model.var.StringValue;
import model.var.Value;

import java.io.IOException;

public class HeapAllocationStatement implements IStatement{
    private final StringValue variableName;
    private final Expression expression;

    public HeapAllocationStatement(StringValue variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState p) throws MyException, IOException {
        MyDictionary<String, Value> symTable = p.getSymTable();
        MyHeap heapTable = p.getHeapTable();

        if (!symTable.isDefined(variableName.getValue()))
            throw new MyException("Not defined!");

        Value resultVariable = symTable.lookup(variableName.getValue());

        if(!(resultVariable.getType() instanceof RefType))
            throw new MyException("Not a refType!");

        RefType resultType = (RefType)resultVariable.getType();
        Value resultEvaluation = expression.eval(symTable, heapTable);

        if(!(resultEvaluation.getType().equals(resultType.getInner())))
            throw new MyException(("Not the same type(" + resultEvaluation.getType().toString()+":"+resultEvaluation.toString()+ "," + resultType.toString()+ ":"+variableName+")") );

        int address = heapTable.add(resultEvaluation);
        symTable.update(variableName.getValue(), new RefValue(address, resultEvaluation.getType()));

        return p;
    }

    public String toString(){
        return "HeapAllocation(" + this.variableName.toString() + "," + this.expression.toString() + ")";

    }
}
