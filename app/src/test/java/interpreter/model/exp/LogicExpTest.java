package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogicExpTest {
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
    }

    @Test
    void evalComputesCorrectLogicalResults() throws MyException {
        IExp trueExp = new ValueExp(new BoolValue(true));
        IExp falseExp = new ValueExp(new BoolValue(false));

        assertEquals(new BoolValue(false), new LogicExp(LogicOp.AND, trueExp, falseExp).eval(symTable));
        assertEquals(new BoolValue(true), new LogicExp(LogicOp.OR, trueExp, falseExp).eval(symTable));
    }

    @Test
    void evalThrowsExceptionOnTypeMismatch() {
        IExp left = new ValueExp(new BoolValue(true));
        IExp right = new ValueExp(new IntValue(5));
        LogicExp invalidExp = new LogicExp(LogicOp.AND, left, right);

        ExpressionException exception = assertThrows(ExpressionException.class, () -> {
            invalidExp.eval(symTable);
        });
        assertEquals("Second operand is not a boolean.", exception.getMessage());
    }
}
