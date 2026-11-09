package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OpenRFileStmtTest {

    @TempDir
    Path dir;

    private PrgState state;
    private ValueExp file;
    private StringValue fileName;

    @BeforeEach
    void setup() throws Exception {
        Path f = dir.resolve("test.in");
        Files.writeString(f, "123\n");
        fileName = new StringValue(f.toString());
        file = new ValueExp(fileName);

        state = PrgState.of(new NopStmt());
    }

    @Test
    void openRFileAddsToFileTable() throws MyException {
        new OpenRFileStmt(file).execute(state);
        assertTrue(state.getFileTable().isDefined(fileName));
    }

    @Test
    void openRFileWithNonStringExpressionThrows() {
        var stmt = new OpenRFileStmt(new ValueExp(new IntValue(1)));
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }

    @Test
    void openingTheSameFileTwiceThrows() throws MyException {
        new OpenRFileStmt(file).execute(state);
        assertThrows(StatementException.class, () -> new OpenRFileStmt(file).execute(state));
    }

    @Test
    void openingMissingFileThrows() {
        ValueExp missingFile = new ValueExp(new StringValue(dir.resolve("missing.txt").toString()));
        assertThrows(StatementException.class, () -> new OpenRFileStmt(missingFile).execute(state));
    }

    @AfterEach
    void tearDown() {
        var fileTable = state.getFileTable();
        for (var key : fileTable.toMap().keySet()) {
            try {
                fileTable.lookup(key).close();
            } catch (Exception e) {
            }
        }
    }
}
