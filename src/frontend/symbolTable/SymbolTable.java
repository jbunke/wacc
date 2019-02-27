package frontend.symbolTable;

import frontend.abstractSyntaxTree.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

  private final String[] reservedWords = new String[]{
          "begin", "is", "end", "skip", "read", "free", "return", "exit",
          "print", "println", "if", "then", "else", "fi", "while", "do", "done",
          "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
          "pair", "len", "ord", "chr", "true", "false", "null"
  };

  private final SymbolTable parent;
  private final Map<String, SymbolCategory> identifierMap;
  private final Map<Node, SymbolTable> childrenMap;

  public SymbolTable(SymbolTable parent) {
    this.parent = parent;
    identifierMap = new HashMap<>();
    childrenMap = new HashMap<>();

    if (this.parent == null) {
      for (String keyword : reservedWords) {
        identifierMap.put(keyword, new Reserved());
      }
    }
  }

  public void add(String identifier, SymbolCategory type) {
    identifierMap.put(identifier, type);
  }

  public SymbolCategory find(String identifier) {
    if (identifierMap.containsKey(identifier)) {
      return identifierMap.get(identifier);
    } else if (parent != null) {
      return parent.find(identifier);
    }
    return null;
  }

  public int getSize() {
    int size = 0;

    Set<String> keys = identifierMap.keySet();
    for (String key : keys) {
      SymbolCategory symbol = identifierMap.get(key);
      if (symbol instanceof Variable) {
        Variable variable = (Variable) symbol;
        size += variable.getType().size();
      }
    }

    return size;
  }

  public SymbolTable getChild(Node scope) {
    if (childrenMap.containsKey(scope)) {
      return childrenMap.get(scope);
    }
    return null;
  }

  public SymbolTable newChild(Node scope) {
    SymbolTable child = new SymbolTable(this);
    childrenMap.put(scope, child);
    return child;
  }
}
