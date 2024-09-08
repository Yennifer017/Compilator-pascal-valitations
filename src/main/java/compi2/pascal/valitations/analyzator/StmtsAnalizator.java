
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ast.SemanticRestrictions;
import compi2.pascal.valitations.semantic.ast.Statement;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class StmtsAnalizator {
    public void validateInternalStmts(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions, 
            List<Statement> internalStmts){
        if(internalStmts !=  null && !internalStmts.isEmpty()){
            for (Statement stmt : internalStmts) {
                stmt.validate(
                        symbolTable, typeTable, semanticErrors, 
                        restrictions
                );
            }
        }
    }
}
