package interpreter.model.type;

import interpreter.model.value.BoolValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoolTypeTest {
    @Test
    void defaultValueIsFalse(){
        BoolType type = new BoolType();
        assertEquals(new BoolValue(false), type.defaultValue());
    }

    @Test
    void equalsWorks(){
        BoolType type1 = new BoolType();
        BoolType type2 = new BoolType();

        assertEquals(type1, type2);
    }
}
