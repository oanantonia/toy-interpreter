package interpreter.model.value;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringValueTest {

    @Test
    void equalsReturnsTrueForSameStrings() {
        StringValue v1 = new StringValue("test.in");
        StringValue v2 = new StringValue("test.in");

        assertEquals(v1, v2);
    }

    @Test
    void equalsReturnsFalseForDifferentStrings() {
        StringValue v1 = new StringValue("test.in");
        StringValue v2 = new StringValue("altfisier.txt");

        assertNotEquals(v1, v2);
    }

    @Test
    void hashCodeIsSameForEqualStrings() {
        StringValue v1 = new StringValue("test.in");
        StringValue v2 = new StringValue("test.in");

        assertEquals(v1.hashCode(), v2.hashCode());
    }
}