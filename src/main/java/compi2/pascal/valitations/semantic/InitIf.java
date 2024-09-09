
package compi2.pascal.valitations.semantic;

import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.util.Position;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class InitIf {
    private Expression expression;
    private Position position;

    public InitIf(Expression expression, Position position) {
        this.expression = expression;
        this.position = position;
    }
    
}
