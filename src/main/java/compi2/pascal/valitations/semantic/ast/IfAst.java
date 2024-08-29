
package compi2.pascal.valitations.semantic.ast;

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
        this.condition = condition;
        super.internalStmts = statements;
    }

    public IfAst(Expression condition, List<Statement> statements, 
            List<IfAst> elifs, ElseAst elseStmt) {
        this.condition = condition;
        this.elifs = elifs;
        this.elseStmt = elseStmt;
    }
    
}
