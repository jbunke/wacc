package frontend.symbolTable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SemanticErrorList {
    private final List<SemanticError> errorList;

    public SemanticErrorList() {
        errorList = new ArrayList<>();
    }

    public void addError(SemanticError error) {
        errorList.add(error);
    }

    public void printErrors(PrintStream stream) {
        for (SemanticError error : errorList) {
            error.print(stream);
        }
    }

    public boolean hasSemanticErrors() {
        return !errorList.isEmpty();
    }
}
