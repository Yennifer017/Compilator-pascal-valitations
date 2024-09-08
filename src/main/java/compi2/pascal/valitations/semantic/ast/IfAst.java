
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
public class IfAst extends ControlStruct{
    private Expression condition;
    private List<IfAst> elifs;
    private ElseAst elseStmt;

    public IfAst(Expression condition, List<Statement> statements) {
        super();
        this.condition = condition;
        super.internalStmts = statements;
    }

    public IfAst(Expression condition, List<Statement> statements, 
            List<IfAst> elifs, ElseAst elseStmt) {
        this.condition = condition;
        this.elifs = elifs;
        this.elseStmt = elseStmt;
        super.internalStmts = statements;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, 
            List<String> semanticErrors, SemanticRestrictions restrictions) {
        super.validateCondition(condition, symbolTable, typeTable, semanticErrors);
        super.validateInternalStmts(symbolTable, typeTable, semanticErrors, 
                restrictions
        );
        if(elifs != null && !elifs.isEmpty()){
            for (IfAst ifAst : elifs) {
                ifAst.validate(symbolTable, typeTable, semanticErrors, restrictions);
            }
        }
        
        if(elseStmt != null){
            elseStmt.validate(symbolTable, typeTable, semanticErrors, restrictions);
        }
        
    }
    
}
