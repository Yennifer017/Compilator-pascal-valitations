
package compi2.pascal.valitations.semantic.obj;

import compi2.pascal.valitations.semantic.expr.Expression;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class ConstDef extends DefAst{
    private Expression expression;
    
    public ConstDef(Label name, Expression expression){
        super.name = name;
        this.expression = expression;
    }
}
