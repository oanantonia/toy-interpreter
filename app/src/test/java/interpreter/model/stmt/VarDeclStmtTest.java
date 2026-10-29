package interpreter.model.stmt;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VarDeclStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void declaringAnIntGivesDefaultZero() throws MyException {
        new VarDeclStmt("v", new IntType()).execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("v"));
    }

    @Test
    void declaringABoolGivesDefaultFalse() throws MyException {
        new VarDeclStmt("b", new BoolType()).execute(state);
        assertEquals(new BoolValue(false), state.getSymTable().lookup("b"));
    }

    @Test
    void declaringTheSameVariableTwiceThrows() throws MyException {
        var stmt = new VarDeclStmt("x", new IntType());
        stmt.execute(state);
        var ex = assertThrows(StatementException.class, () -> stmt.execute(state));
        assertEquals("Variable x was already declared.", ex.getMessage());
    }
}