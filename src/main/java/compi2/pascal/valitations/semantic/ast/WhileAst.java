
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.analysis.actree.PassActTree;
import compi2.pascal.valitations.semantic.SemanticRestrictions;
import compi2.pascal.valitations.analysis.symbolt.SymbolTable;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ReturnCase;
import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.util.Index;
import compi2.pascal.valitations.util.Position;
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

    public WhileAst(Expression condition, List<Statement> internalStmts, Position initPos) {
        super(initPos);
        this.condition = condition;
        super.internalStmts = internalStmts;
    }

    @Override
    public ReturnCase validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        super.validateCondition(condition, symbolTable, typeTable, semanticErrors);
        return super.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                new SemanticRestrictions(
                        true, 
                        true, 
                        restrictions.getReturnType(), 
                        restrictions.getReturnType())
        );
        
    }

    @Override
    public PassActTree getActivationNodeTree(Index index) {
        return actTreeGen.generatePass(internalStmts, index);
    }
    
}
