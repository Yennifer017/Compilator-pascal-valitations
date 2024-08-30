
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
public class SimpleCase {
    private List<Expression> labels;
    private List<Statement> statements;

    public SimpleCase(List<Expression> labels, List<Statement> statements) {
        this.labels = labels;
        this.statements = statements;
    }
    
}
