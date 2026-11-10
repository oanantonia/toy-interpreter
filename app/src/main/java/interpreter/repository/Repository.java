package interpreter.repository;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import interpreter.exceptions.RepositoryException;
import interpreter.model.PrgState;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public record Repository(IList<PrgState> states, String logFilePath) implements IRepository {
    public Repository(PrgState initialState, String logFilePath) {
        this(new MyList<>(), logFilePath);
        this.states.add(initialState);
    }

    @Override
    public PrgState getCrtPrg() throws ADTException{
        return states.get(0);
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try (var logFile = new PrintWriter(
                new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.println(getCrtPrg().toLogString());
        } catch (IOException e) {
            throw new RepositoryException("Cannot write the log file " + logFilePath
                    + ": " + e.getMessage());
        }
    }
}
