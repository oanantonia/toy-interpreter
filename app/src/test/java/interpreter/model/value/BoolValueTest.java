package interpreter.model.value;

import interpreter.model.type.BoolType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoolValueTest {
    @Test
    void getTypeReturnsBoolType() {
        BoolValue val = new BoolValue(true);
        assertEquals(new BoolType(), val.getType());
    }

    @Test
    void equalsWorksCorrectly() {
        BoolValue val1 = new BoolValue(true);
        BoolValue val2 = new BoolValue(true);
        BoolValue val3 = new BoolValue(false);

        assertEquals(val1, val2);
        assertNotEquals(val1, val3);
    }
}
