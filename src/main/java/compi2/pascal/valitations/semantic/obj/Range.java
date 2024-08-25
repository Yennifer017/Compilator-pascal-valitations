
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.semantic.expr.Expression;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Range {
    Expression init;
    Expression end;

    public Range(Expression init, Expression end) {
        this.init = init;
        this.end = end;
    }
    
}
