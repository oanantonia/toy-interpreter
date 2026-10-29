package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NopStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
        state.getExeStack().pop();
    }

    @Test
    void leavesTheWholeStateUnchanged() throws MyException {
        state.getSymTable().put("v", new IntValue(5));
        new NopStmt().execute(state);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(5), state.getSymTable().lookup("v"));
        assertEquals(0, state.getOut().size());
    }
}