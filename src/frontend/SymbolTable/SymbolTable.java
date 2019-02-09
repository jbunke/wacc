package frontend.SymbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private final String[] reserved = new String[]{
      "begin", "is", "end", "skip", "read", "free", "return", "exit",
      "print", "println", "if", "then", "else", "fi", "while", "do", "done",
      "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
      "pair", "len", "ord", "chr", "true", "false", "null"
  };

  private final SymbolTable parent;
  private final Map<String, Identifier> mapping;

  public SymbolTable(SymbolTable parent) {
    this.parent = parent;
    mapping = new HashMap<String, Identifier>();
  }

  public void add(String name, Identifier obj){
    mapping.put(name, obj);
  }

  public Identifier lookUp(String name){
    if (mapping.containsKey(name)) {
      return mapping.get(name);

    } else if (parent != null){
      return parent.lookUp(name);

    } else {
      return null;
    }
  }
}