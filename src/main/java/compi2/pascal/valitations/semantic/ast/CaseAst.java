
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
public class CaseAst extends Statement{
    private Expression expression;
    private List<SimpleCase> cases;
    private ElseAst elseAst;

    public CaseAst(Expression expression, List<SimpleCase> cases, ElseAst elseAst) {
        this.expression = expression;
        this.cases = cases;
        this.elseAst = elseAst;
    }

    @Override
    public void validate(SymbolTable symbolTable, TypeTable typeTable, List<String> semanticErrors, SemanticRestrictions restrictions) {
        
    }
    
}
