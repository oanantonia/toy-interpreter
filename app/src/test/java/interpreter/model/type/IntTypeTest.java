package interpreter.model.type;

import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntTypeTest {
    @Test
    void defaultValueIsZero() {
        IntType type = new IntType();
        assertEquals(new IntValue(0), type.defaultValue());
    }

    @Test
    void equalsWorks() {
        IntType type1 = new IntType();
        IntType type2 = new IntType();
        assertEquals(type1, type2);
    }
}
