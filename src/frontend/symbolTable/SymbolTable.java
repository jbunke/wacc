package frontend.symbolTable;

import frontend.abstractSyntaxTree.Node;

import java.util.*;

public class SymbolTable {

  private static final String[] reservedWords = new String[]{
          "begin", "is", "end", "skip", "read", "free", "return", "exit",
          "print", "println", "if", "then", "else", "fi", "while", "do", "done",
          "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
          "pair", "len", "ord", "chr", "true", "false", "null"
  };
  public static final String ARG_PREFIX = "!arg_";

  private final SymbolTable parent;
  private final Map<String, SymbolCategory> identifierMap;
  private final List<String> contents;
  private final Map<Node, SymbolTable> childrenMap;

  public SymbolTable(SymbolTable parent) {
    this.parent = parent;
    identifierMap = new HashMap<>();
    childrenMap = new HashMap<>();
    contents = new ArrayList<>();

    if (this.parent == null) {
      for (String keyword : reservedWords) {
        identifierMap.put(keyword, new Reserved());
      }
    }
  }

  public void populateOnDeclare(String identifier) {
    if (!contents.contains(identifier) &&
            (identifierMap.containsKey(identifier) ||
                    identifier.equals("_FUNC_") ||
      (identifier.startsWith(ARG_PREFIX) &&
              identifierMap.containsKey(
                      identifier.substring(ARG_PREFIX.length()))))) {
      contents.add(identifier);
    }
  }

  public int fetchOffset(String identifier) {
    if (!identifierMap.containsKey(identifier)) {
      return getSize() + parent.fetchOffset(identifier);
    }
    if (contents.contains(ARG_PREFIX + identifier)) {
      identifier = ARG_PREFIX + identifier;
    }
    int offset = getSize();
    for (String content : contents) {
      if (content.equals("_FUNC_")) {
        offset += 4;
      } else {
        String check = content.startsWith(ARG_PREFIX) ?
                content.substring(ARG_PREFIX.length()) : content;
        Variable variable = (Variable) identifierMap.get(check);
        offset -= variable.getType().size();
        if (content.equals(identifier)) break;
      }
    }
    return offset;
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

  private boolean contentsContains(String check) {
    if (contents.contains(check)) {
      return true;
    } else if (parent != null) {
      return parent.contentsContains(check);
    }
    return false;
  }

  public int getSize() {
    int size = 0;

    Set<String> keys = identifierMap.keySet();
    for (String key : keys) {
      SymbolCategory symbol = identifierMap.get(key);
      if (symbol instanceof Variable &&
              !contentsContains(ARG_PREFIX + key)) {
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
