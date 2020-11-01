package tests.model.var;

import model.var.BoolValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoolValue {
    @Test
    public void BoolValueGetValue__ReturnsFalse(){
        BoolValue val = new BoolValue(false);
        assertEquals(false, val.getValue(), "Returns False");
    }

    @Test
    public void BoolValueGetValue__ReturnsTrue(){
        BoolValue val = new BoolValue(true);
        assertEquals(true, val.getValue(), "Returns true");
    }

}
