package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssignStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void assignmentUpdatesTheVariable() throws MyException {
        state.getSymTable().put("v", new IntValue(0));
        new AssignStmt("v", new ValueExp(new IntValue(2))).execute(state);
        assertEquals(new IntValue(2), state.getSymTable().lookup("v"));
    }

    @Test
    void assigningToAnUndeclaredVariableThrows() {
        var stmt = new AssignStmt("x", new ValueExp(new IntValue(2)));
        var ex = assertThrows(StatementException.class, () -> stmt.execute(state));
        assertTrue(ex.getMessage().contains("x"));
    }

    @Test
    void assigningTheWrongTypeThrows() {
        state.getSymTable().put("b", new BoolValue(false));
        var stmt = new AssignStmt("b", new ValueExp(new IntValue(1)));
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }
}