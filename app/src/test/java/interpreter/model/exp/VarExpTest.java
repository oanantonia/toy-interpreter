package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IntValue;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VarExpTest {
    @Test
    void evalReturnsVariableValueWhenDefined() throws MyException {
        IDictionary<String, IValue> symTable = new MyDictionary<>();
        symTable.put("x", new IntValue(5));

        VarExp exp = new VarExp("x");
        assertEquals(new IntValue(5), exp.eval(symTable));
    }

    @Test
    void evalThrowsExpressionExceptionWhenVariableNotDefined() {
        VarExp exp = new VarExp("missingVar");
        var ex = assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
        assertEquals("Variable missingVar is not defined.", ex.getMessage());
    }
}