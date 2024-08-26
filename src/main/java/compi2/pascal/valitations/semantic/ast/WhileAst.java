
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.semantic.expr.Expression;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class WhileAst extends ControlStruct{
    private Expression condition;
}
