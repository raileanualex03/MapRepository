package tests.model.adt;

import model.adt.MyDictionary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyDictionary {

    @Test
    public void DictionaryLookup__ReturnsValue(){
        MyDictionary<Integer, Integer> dictionary = new MyDictionary<>();
        dictionary.add(1, 2);
        assertEquals(2, dictionary.lookup(1), "Returns 2");
    }

    @Test
    public void DictionaryIsDefined__ReturnsTrue(){
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.add("1", 2);
        assertTrue( dictionary.isDefined("1"), "Returns True");
    }

    @Test
    public void DictionaryIsDefined__ReturnsFals(){
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.add("2", 2);
        assertFalse( dictionary.isDefined("1"), "Returns False");
    }

}
