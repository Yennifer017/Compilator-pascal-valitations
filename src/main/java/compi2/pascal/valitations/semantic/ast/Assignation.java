
package compi2.pascal.valitations.semantic.ast;

import compi2.pascal.valitations.semantic.expr.Expression;
import compi2.pascal.valitations.semantic.obj.Label;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Assignation extends Statement{
    private Label variable;
    private Expression expToAsign;
    
    public Assignation(Label variable, Expression expToAsign){
        this.variable = variable;
        this.expToAsign = expToAsign;
    }
}
