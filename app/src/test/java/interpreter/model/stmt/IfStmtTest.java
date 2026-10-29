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

class IfStmtTest {
    private final IStmt thenS = new PrintStmt(new ValueExp(new IntValue(1)));
    private final IStmt elseS = new PrintStmt(new ValueExp(new IntValue(2)));
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
        state.getExeStack().pop();
    }

    @Test
    void trueConditionPushesOnlyTheThenBranch() throws MyException {
        new IfStmt(new ValueExp(new BoolValue(true)), thenS, elseS).execute(state);
        assertSame(thenS, state.getExeStack().pop());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void falseConditionPushesOnlyTheElseBranch() throws MyException {
        new IfStmt(new ValueExp(new BoolValue(false)), thenS, elseS).execute(state);
        assertSame(elseS, state.getExeStack().pop());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void nonBooleanConditionThrows() {
        var stmt = new IfStmt(new ValueExp(new IntValue(1)), thenS, elseS);
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }
}