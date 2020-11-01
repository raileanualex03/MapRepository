package tests.model.exp;

import exceptions.MyException;
import model.adt.MyDictionary;
import model.exp.ArithmeticExpression;
import model.exp.Expression;
import model.exp.ValueExpression;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestValueExpression {
    @Test
    public void ValueExpressionEval__ReturnsValue() throws MyException {
        MyDictionary<String, Value> table = new MyDictionary<String, Value>();
        Expression exp1 = new ValueExpression(new IntValue(3));
        IntValue val = (IntValue) exp1.eval(table);
        assertEquals(3, val.getValue(), "result is 3");
    }
}
