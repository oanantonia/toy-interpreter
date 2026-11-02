package interpreter.model.type;

import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringTypeTest {

    @Test
    void defaultValueIsAnEmptyStringValue() {
        StringType type = new StringType();
        assertEquals(new StringValue(""), type.defaultValue());
    }

    @Test
    void toStringReturnsCorrectName() {
        StringType type = new StringType();
        assertEquals("string", type.toString());
    }
}