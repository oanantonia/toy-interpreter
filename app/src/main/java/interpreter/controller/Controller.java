package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.IStack;
import interpreter.model.stmt.IStmt;
import interpreter.repository.IRepository;

public record Controller(IRepository repo) {

    public PrgState oneStep(PrgState state) throws MyException {
        IStack<IStmt> stk = state.getExeStack();

        if (stk.isEmpty()) {
            throw new MyException("Execution stack is empty.");
        }

        IStmt crtStmt = stk.pop();

        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();

        System.out.println(prg);

        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            System.out.println(prg);
        }
    }
}
