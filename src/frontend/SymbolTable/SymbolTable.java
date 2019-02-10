package frontend.SymbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    //TODO populate reserved words into top-level Symbol Table

    private final String[] reservedWords = new String[]{
            "begin", "is", "end", "skip", "read", "free", "return", "exit",
            "print", "println", "if", "then", "else", "fi", "while", "do", "done",
            "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
            "pair", "len", "ord", "chr", "true", "false", "null"
    };

    private final SymbolTable parent;
    private final Map<String, Identifier> identifierMap;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        identifierMap = new HashMap<>();
    }

    public void add(String identifier, Identifier type) {
        identifierMap.put(identifier, type);
    }

    public Identifier fetchType(String identifier) {
        if (identifierMap.containsKey(identifier)) {
            return identifierMap.get(identifier);
        } else if (parent != null) {
            return parent.fetchType(identifier);
        }
        return null;
    }

}
