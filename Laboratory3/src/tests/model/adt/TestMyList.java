package tests.model.adt;

import model.adt.MyList;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyList {
    @Test
    public void List__ReturnsValue(){
        MyList<Integer> list = new MyList<>();
        list.add(3);
        assertEquals(3, list.pop(), "Returns value");
    }

}
