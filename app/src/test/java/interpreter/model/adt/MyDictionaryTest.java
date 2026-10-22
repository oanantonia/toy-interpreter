package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyDictionaryTest {
    private IDictionary<String, Integer> dictionary;

    @BeforeEach
    void setUp(){
        dictionary = new MyDictionary<>();
    }

    @Test
    void missingKeyInLookupThrowsADTException(){
        ADTException exception = assertThrows(ADTException.class, () -> {
            dictionary.lookup("a");
        });

        assertEquals("Key a is not defined.", exception.getMessage());
    }

    @Test
    void missingKeyInUpdateThrowsADTException(){
        ADTException exception = assertThrows(ADTException.class, () -> {
            dictionary.update("a", 1);
        });

        assertEquals("Key a is not defined.", exception.getMessage());
    }

    @Test
    void putAndLookup() throws ADTException{
        dictionary.put("a", 0);
        assertTrue(dictionary.isDefined("a"));
        assertEquals(0, dictionary.lookup("a"));
    }

    @Test
    void updateChangesValue() throws ADTException {
        dictionary.put("a", 0);
        dictionary.update("a", 1);
        assertEquals(1, dictionary.lookup("a"));
    }
}
