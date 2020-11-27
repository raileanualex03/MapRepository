package model.exp;
import exceptions.MyException;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.types.IntType;
import model.var.IntValue;
import model.var.Value;

public class ArithmeticExpression implements Expression{
    int operation;
    Expression firstExpression, secondExpression;

    public ArithmeticExpression(int op, Expression e1, Expression e2){
        this.operation = op;
        firstExpression = e1;
        secondExpression = e2;
    }


    @Override
    public Value eval(MyDictionary<String, Value> table, MyHeap heap) throws MyException {
        Value firstVal, secondVal = null;
        firstVal = null;
        try {
            firstVal = firstExpression.eval(table, heap);
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            secondVal = secondExpression.eval(table, heap);
        } catch (MyException e) {
            e.printStackTrace();
        }

        if ( operation > 4 || operation < 1){
            throw new MyException("Invalid operation");
        }

        if (!firstVal.getType().equals(new IntType())){
            throw new MyException("Error: First operand not integer");
        }

        if (!secondVal.getType().equals(new IntType())){
            throw new MyException("Error: Second operand not integer");
        }
        IntValue first = (IntValue) firstVal;
        IntValue second = (IntValue) secondVal;

        int firstNumber, secondNumber;
        firstNumber = first.getValue();
        secondNumber = second.getValue();

        switch(operation){
            case 1:
                return new IntValue(firstNumber + secondNumber);
            case 2:
                return new IntValue(firstNumber - secondNumber);
            case 3:
                return new IntValue(firstNumber*secondNumber);
            case 4:
                if (secondNumber == 0)
                    throw new MyException("Error: Divisor is 0");
                else
                    return new IntValue(firstNumber/secondNumber);
            default:
                throw new MyException("Error: Bad operation");
        }

    }

    public String toString(){
        return switch (operation) {
            case 1 -> firstExpression + "+" + secondExpression;
            case 2 -> firstExpression + "-" + secondExpression;
            case 3 -> firstExpression + "*" + secondExpression;
            case 4 -> firstExpression + "/" + secondExpression;
            default -> "Bad operation";
        };
    }
}
