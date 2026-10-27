package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public record IfStmt(IExp cond, IStmt thenS, IStmt elseS) implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue result = cond.eval(state.getSymTable());

        if(!(result instanceof BoolValue(boolean condition))){
            throw new StatementException("Conditional expression is not a boolean.");
        }

        if (condition) {
            state.getExeStack().push(thenS);
        } else {
            state.getExeStack().push(elseS);
        }

        return null;
    }

    @Override
    public String toString() {
        return "(IF(" + cond + ") THEN(" + thenS + ") ELSE(" + elseS + "))";
    }
}
