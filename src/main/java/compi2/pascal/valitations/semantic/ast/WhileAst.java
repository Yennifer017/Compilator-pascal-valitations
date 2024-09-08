
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.expr.Expression;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class WhileAst extends ControlStruct{
    private Expression condition;

    public WhileAst(Expression condition, List<Statement> internalStmts) {
        super();
        this.condition = condition;
        super.internalStmts = internalStmts;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        super.validateCondition(condition, symbolTable, typeTable, semanticErrors);
        super.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                new SemanticRestrictions(
                        true, 
                        true, 
                        restrictions.needReturnVal(), 
                        restrictions.getReturnType())
        );
        
    }
    
}
