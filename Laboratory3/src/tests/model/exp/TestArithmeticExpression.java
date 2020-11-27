package tests.model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.exp.ArithmeticExpression;
import model.exp.Expression;
import model.exp.ValueExpression;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArithmeticExpression {
    @Test
    public void ArithmeticExpressionEvalSum__ReturnsValue() throws MyException {
        MyDictionary<String, Value> table = new MyDictionary<String, Value>();
        MyHeap heap = new MyHeap();
        Expression exp1 = new ValueExpression(new IntValue(3));
        Expression exp2 = new ValueExpression(new IntValue(3));
        ArithmeticExpression result = new ArithmeticExpression(1, exp1, exp2);
        IntValue r = (IntValue) result.eval(table, heap);
        assertEquals(6,r.getValue(), "result should be 6");

    }

    @Test
    public void ArithmeticExpressionEvalSum__ThrowsException() throws MyException{
            MyDictionary<String, Value> table = new MyDictionary<String, Value>();
            Expression exp1 = new ValueExpression(new IntValue(3));
            Expression exp2 = new ValueExpression(new BoolValue(false));
            MyHeap heap = new MyHeap();
            ArithmeticExpression result = new ArithmeticExpression(1, exp1, exp2);
            try{
                result.eval(table, heap);
                assert(false);
            }
            catch (MyException exception){
                assert(true);

        }
    }

    @Test
    public void ArithmeticExpressionEvalDivideByZero__ThrowsException() throws MyException{
        MyDictionary<String, Value> table = new MyDictionary<String, Value>();
        Expression exp1 = new ValueExpression(new IntValue(3));
        Expression exp2 = new ValueExpression(new IntValue(0));
        MyHeap heap = new MyHeap();
        ArithmeticExpression result = new ArithmeticExpression(4, exp1, exp2);
        try{
            result.eval(table, heap);
            assert(false);
        }
        catch (MyException exception){
            assert(true);

        }
    }
}
