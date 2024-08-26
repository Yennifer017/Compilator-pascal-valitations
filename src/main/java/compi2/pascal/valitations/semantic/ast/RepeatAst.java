
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
public class RepeatAst extends ControlStruct {
    private Expression condition;

    public RepeatAst(Expression condition, List<Statement> internalStmts) {
        this.condition = condition;
        super.internalStmts = internalStmts;
    }
    
}
