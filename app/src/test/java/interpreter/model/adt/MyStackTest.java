package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {
    private IStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>();
    }

    @Test
    void pushAndPopMaintainLIFOOrder() throws ADTException {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popFromEmptyStackThrowsADTException() {
        ADTException exception = assertThrows(ADTException.class, () -> {
            stack.pop();
        });

        assertEquals("Cannot pop from an empty stack.", exception.getMessage());
    }

    @Test
    void toListReturnsTopOfStackFirst() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(List.of(3, 2, 1), stack.toList());
    }
}