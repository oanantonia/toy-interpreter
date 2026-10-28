package interpreter.repository;

import interpreter.exceptions.ADTException;
import interpreter.model.PrgState;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyList;

public record Repository(IList<PrgState> states) implements IRepository {
    public Repository(PrgState initialState) {
        this(new MyList<>());
        this.states.add(initialState);
    }
    @Override
    public PrgState getCrtPrg() {
        try {
            return states.get(0);
        } catch (ADTException e) {
            return null;
        }
    }
}
