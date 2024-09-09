
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.util.ErrorsRep;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class StmtsAnalizator {
    private ErrorsRep errorsRep;
    
    public StmtsAnalizator(){
        
    }
    
    public ReturnCase validateInternalStmts(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions, 
            List<Statement> internalStmts){
        if(internalStmts !=  null){
            boolean allCovered = false;
            for (int i = 0; i < internalStmts.size(); i++) {
                Statement stmt = internalStmts.get(0);
                ReturnCase retCase = stmt.validate(
                        symbolTable, typeTable, semanticErrors, 
                        restrictions
                );
                if(allCovered){
                    semanticErrors.add(errorsRep.unrachableCodeError(stmt.getInitPos()));
                }
                if(retCase.isAllScenaries() && i != internalStmts.size() && !allCovered){
                    semanticErrors.add(errorsRep.unrachableCodeError(stmt.getInitPos()));
                    allCovered = true;
                } else if(retCase.isAllScenaries() && i == internalStmts.size()){
                    return new ReturnCase(true);
                }
            }
        }
        return new ReturnCase(false);
    }
}
