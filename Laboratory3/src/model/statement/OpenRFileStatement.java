package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyStack;
import model.exp.Expression;
import model.types.StringType;
import model.var.StringValue;
import model.var.Value;

import java.io.*;

public class OpenRFileStatement implements IStatement {
    final private Expression expression;

    public OpenRFileStatement(Expression expression) {
        this.expression = expression;
    }



    @Override
    public ProgramState execute(ProgramState ps) throws MyException, FileNotFoundException {
        MyStack<IStatement> stack = ps.getExeStack();
        MyDictionary<String, Value> table = ps.getSymTable();
        MyDictionary<String, BufferedReader> fileTable = ps.getFileTable();
        Value val = expression.eval(table);

        if (val.getType().equals(new StringType())){
            String path = ((StringValue)val).getValue();
            if (fileTable.isDefined(((StringValue) val).getValue())){
                 throw new MyException("Already existing");
            }
            else{
                BufferedReader objReader = null;
                try {
                    objReader = new BufferedReader(new FileReader(path));
                }
                catch(IOException e) {
                    System.out.println(e.toString());
                }
                fileTable.add(path, objReader);
            }
        }
        else
            throw new MyException("Wrong type");

        return ps;
    }

}
