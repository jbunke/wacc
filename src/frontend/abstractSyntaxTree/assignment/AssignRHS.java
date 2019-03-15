package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import shell.structural.Heap;

import java.util.Stack;

public interface AssignRHS extends AssignLHS {
  int ADDR_SIZE = 4;

  Type getType(SymbolTable symbolTable);

  void generateAssembly(AssemblyGenerator generator,
                        SymbolTable symbolTable,
                        Stack<Register.ID> available);

  /**
   * All AssignRHS implementing classes override this to evaluate their values
   *
   * @param symbolTable symbolTable in scope. Will either be
   *                    WACCShell.symbolTable or a descendant of
   *                    WACCShell.symbolTable (child, grandchild, etc.)
   * @param heap Heap in scope
   *
   * @return The value of the RHS; either ExpressionNode, FunctionCallNode,
   * etc., represented as an Object
   * */
  Object evaluate(SymbolTable symbolTable, Heap heap);
}
