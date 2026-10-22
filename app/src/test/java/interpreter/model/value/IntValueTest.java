package interpreter.model.value;

import interpreter.model.type.IntType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {
    @Test
    void getTypeReturnsIntType() {
        IntValue val = new IntValue(5);
        assertEquals(new IntType(), val.getType());
    }

    @Test
    void equalsWorks() {
        IntValue val1 = new IntValue(1);
        IntValue val2 = new IntValue(1);
        IntValue val3 = new IntValue(2);

        assertEquals(val1, val2);
        assertNotEquals(val1, val3);
    }
}