package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyListTest {
    IList<Integer> list;

    @BeforeEach
    void setUp(){
        list = new MyList<Integer>();
    }

    @Test
    void indexOutOfBoundsThrowsADTException(){
        ADTException exception1 = assertThrows(ADTException.class, () -> {
            list.get(-1);
        });

        assertEquals("Index out of bounds.", exception1.getMessage());

        ADTException exception2 = assertThrows(ADTException.class, () -> {
            list.get(10);
        });

        assertEquals("Index out of bounds.", exception2.getMessage());
    }

    @Test
    void addGetAndSizeWork() throws ADTException{
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

}
