
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import java.util.List;

/**
 *
 * @author blue-dragon
 */
public class ElseAst extends ControlStruct{
    
    public ElseAst(List<Statement> stmts){
        super.internalStmts = stmts;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        super.validateInternalStmts(symbolTable, typeTable, semanticErrors, restrictions);
    }
}
