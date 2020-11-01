package tests.model.var;

import model.types.IntType;
import model.var.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntValue {
    @Test
    public void IntValueGetValue__ReturnsValue(){
        IntValue value = new IntValue(3);
        assertEquals(3, value.getValue(), "Returns good value");
    }



    @Test
    public void IntTypeEquals__ReturnsFalse(){
        IntValue value1 = new IntValue(3);
        IntValue value2 = new IntValue(4);
        assertFalse(value1.equals(value2), "The values are different");
    }
}
