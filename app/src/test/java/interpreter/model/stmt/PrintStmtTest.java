package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrintStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void appendsTheEvaluatedValueToOut() throws MyException {
        new PrintStmt(new ValueExp(new IntValue(7))).execute(state);
        assertEquals(1, state.getOut().size());
        assertEquals(new IntValue(7), state.getOut().get(0));
    }
}