package model.statement;

import exceptions.MyException;
import model.ProgramState;
import model.adt.MyDictionary;
import model.adt.MyStack;
import model.exp.Expression;
import model.types.Type;
import model.var.Value;

public class AssignStatement implements IStatement {
    final private String id;
    final private Expression exp;

    public AssignStatement(String id, Expression exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws MyException {
        MyStack<IStatement> stack = ps.getExeStack();
        MyDictionary<String, Value> table = ps.getSymTable();

        if (table.isDefined(id)) {
            Value value = null;
            try {
                value = exp.eval(table, ps.getHeapTable());
            } catch (MyException e) {
                e.printStackTrace();
            }
            Type type = table.lookup(id).getType();
            if (!value.getType().equals(type))
                throw new MyException("No match between expressions");
            else
                table.update(id, value);
        } else
            throw new MyException("This variable wasn't declared previously");
        return ps;
    }

    public String toString(){
        return id + " =" + exp.toString();
    }


}
