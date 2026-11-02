package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalExpTest {

    @Test
    void lessThanEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.LT, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(2)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.LT, new ValueExp(new IntValue(2)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void lessThanOrEqualEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.LE, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.LE, new ValueExp(new IntValue(2)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void equalEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.EQ, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.EQ, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(2)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void notEqualEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.NE, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(2)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.NE, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void greaterThanEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.GT, new ValueExp(new IntValue(2)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.GT, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(1)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void greaterThanOrEqualEvaluatesCorrectly() throws MyException {
        IExp exprTrue = new RelationalExp(RelOp.GE, new ValueExp(new IntValue(2)), new ValueExp(new IntValue(2)));
        assertEquals(new BoolValue(true), exprTrue.eval(new MyDictionary<>()));

        IExp exprFalse = new RelationalExp(RelOp.GE, new ValueExp(new IntValue(1)), new ValueExp(new IntValue(2)));
        assertEquals(new BoolValue(false), exprFalse.eval(new MyDictionary<>()));
    }

    @Test
    void nonIntegerLeftOperandThrowsExpressionException() {
        IExp invalidExpr = new RelationalExp(RelOp.LT, new ValueExp(new BoolValue(true)), new ValueExp(new IntValue(1)));
        ExpressionException exception = assertThrows(ExpressionException.class, () ->
                invalidExpr.eval(new MyDictionary<>()));
        assertEquals("First operand is not an integer.", exception.getMessage());
    }

    @Test
    void nonIntegerRightOperandThrowsExpressionException() {
        IExp invalidExpr = new RelationalExp(RelOp.GT, new ValueExp(new IntValue(1)), new ValueExp(new BoolValue(false)));
        ExpressionException exception = assertThrows(ExpressionException.class, () ->
                invalidExpr.eval(new MyDictionary<>()));
        assertEquals("Second operand is not an integer.", exception.getMessage());
    }
}
