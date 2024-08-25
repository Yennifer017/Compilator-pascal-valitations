
package compi2.pascal.valitations.semantic.expr;

import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Setter @Getter
public class UnaryOperation extends Expression{
    private DefiniteOperation operation;
    private Expression expression;

    public UnaryOperation(DefiniteOperation operation, Expression expression, Position pos) {
        this.operation = operation;
        this.expression = expression;
        super.pos = pos;
    }

}
