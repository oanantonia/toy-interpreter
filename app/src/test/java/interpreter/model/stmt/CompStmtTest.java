package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompStmtTest {
    private final IStmt first = new PrintStmt(new ValueExp(new IntValue(1)));
    private final IStmt second = new PrintStmt(new ValueExp(new IntValue(2)));
    private CompStmt comp;
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        comp = new CompStmt(first, second);
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), comp);
        state.getExeStack().pop();
    }

    @Test
    void pushesSecondThenFirstSoFirstRunsNext() throws MyException {
        comp.execute(state);
        assertSame(first, state.getExeStack().pop());
        assertSame(second, state.getExeStack().pop());
        assertTrue(state.getExeStack().isEmpty());
    }
}