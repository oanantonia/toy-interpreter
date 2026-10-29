package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IStack;
import interpreter.model.stmt.IStmt;
import interpreter.repository.IRepository;

public record Controller(IRepository repo, boolean displayFlag) {
    private void display(PrgState state) {
        if (displayFlag) {
            System.out.println(state);
        }
    }

    public PrgState oneStep(PrgState state) throws MyException {
        IStack<IStmt> stk = state.getExeStack();

        if (stk.isEmpty()) {
            throw new StatementException("Execution stack is empty.");
        }

        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();

        display(prg);

        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            display(prg);
        }
    }
}
