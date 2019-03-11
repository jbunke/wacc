package frontend.symbolTable;

import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;

import java.util.*;

/**
 * A table of all of the symbols in the scope of a WACC program.
 * <p>
 * The SymbolTable(s) associated with a WACC program and their
 * children are created and populated from the root (ProgramNode ST)
 * down in the front-end phase of the compiler.
 * <p>
 * Relevant STs are accessed for the back-end by calls to getChild() with the
 * Node for which the ST is responsible.
 * <p>
 * The field contents is populated during the backend by function calls
 * (for arguments) and by declaration statements (for variables).
 */
public class SymbolTable {

  /**
   * The reserved keywords of the WACC language.
   * These are populated as Reserved symbols in the constructor
   * if the SymbolTable is root level (has no parent).
   */
  private static final String[] reservedWords = new String[]{
          "begin", "is", "end", "skip", "read", "free", "return", "exit",
          "print", "println", "if", "then", "else", "fi", "while", "do", "done",
          "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
          "pair", "len", "ord", "chr", "true", "false", "null"
  };
  public static final String ARG_PREFIX = "!arg_";

  private final Node scope;
  private final SymbolTable parent;
  private final Map<String, SymbolCategory> identifierMap;
  private final List<String> contents;
  private final Map<Node, SymbolTable> childrenMap;

  private int argLoadingOffset;

  /**
   * @param parent The SymbolTable associated with the
   *               immediately outer scope of the current SymbolTable
   * @param scope  Represented as a Node:
   *               Designed to be ProgramNode, FunctionDeclarationNode,
   *               InnerScopeStatementNode, etc.
   */
  public SymbolTable(SymbolTable parent, Node scope) {
    this.parent = parent;
    this.scope = scope;
    identifierMap = new HashMap<>();
    childrenMap = new HashMap<>();
    contents = new ArrayList<>();

    if (this.parent == null) {
      for (String keyword : reservedWords) {
        identifierMap.put(keyword, new Reserved());
      }
    }

    argLoadingOffset = 0;
  }

  /**
   * @param identifier The symbol to populate; either a variable or a
   *                   function argument
   *                   <p>
   *                   ARG_PREFIX is defined as "!arg_", which cannot be replicated as
   *                   an identifier name in WACC as it violates the grammar. This allows
   *                   for the symbol table to effectively distinguish between variables
   *                   and function arguments for allocating stack offsets.
   */
  public void populateOnDeclare(String identifier) {
    if (!contents.contains(identifier) &&
            (identifierMap.containsKey(identifier) ||
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
    int offset = getSize(true);
    for (String content : contents) {
      String check = content.startsWith(ARG_PREFIX) ?
              content.substring(ARG_PREFIX.length()) : content;
      Variable variable = (Variable) identifierMap.get(check);
      offset -= variable.getType().size();
      if (content.equals(identifier)) break;
    }
    return identifier.startsWith(ARG_PREFIX) ? (offset + 4 + argLoadingOffset)
            : offset + argLoadingOffset;
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
    return getSize(false);
  }

  /**
   * @param withArgs Flag for whether size of variables in ST scope
   *                 should include function arguments
   * @return The sum of the size of the types of all of the Variables
   * in the identifierMap
   */
  private int getSize(boolean withArgs) {
    int size = 0;

    Set<String> keys = identifierMap.keySet();
    for (String key : keys) {
      SymbolCategory symbol = identifierMap.get(key);
      if (symbol instanceof Variable &&
              (withArgs || !contentsContains(ARG_PREFIX + key))) {
        Variable variable = (Variable) symbol;
        size += variable.getType().size();
      }
    }

    return size;
  }

  public void incrementArgLoadingOffset(int increment) {
    argLoadingOffset += increment;
  }

  public void resetArgLoadingOffset() {
    argLoadingOffset = 0;
  }

  public SymbolTable functionTable() {
    if (scope instanceof FunctionDefinitionNode) {
      return this;
    }
    return parent.functionTable();
  }

  /**
   * @param scope The scope of child ST that should have been created in
   *              the front-end
   * @return Allows us to get pre-populated child ST from the front-end
   */
  public SymbolTable getChild(Node scope) {
    if (childrenMap.containsKey(scope)) {
      return childrenMap.get(scope);
    }
    return null;
  }

  public SymbolTable newChild(Node scope) {
    SymbolTable child = new SymbolTable(this, scope);
    childrenMap.put(scope, child);
    return child;
  }
}
